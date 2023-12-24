package my_click;

import my_click.domain.Card;
import my_click.domain.TransActionHistory;
import my_click.domain.User;
import my_click.enums.CardTypeEnums;
import my_click.enums.StatusEnum;
import my_click.service.CardService;
import my_click.service.TransActionService;
import my_click.service.UserService;
import my_click.service.impl.CardServiceImpl;
import my_click.service.impl.TransActionServiceImpl;
import my_click.service.impl.UserServiceImpl;
import my_click.utils.StringUtils;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    static Scanner scStr = new Scanner(System.in);
    static Scanner scInt = new Scanner(System.in);
    static UserService userService = new UserServiceImpl();
    static CardService cardService = new CardServiceImpl();

    static TransActionService transActionService = new TransActionServiceImpl();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        while (true) {
            System.out.println("""
                    1. Login :
                    2. Register :
                    0. Exit :
                    """);

            int userInput = scInt.nextInt();

            if (userInput == 0) {
                break;
            }

            if (userInput == 1) {
                System.out.println("Enter the password : ");
                String password = scStr.nextLine();
                System.out.println("Enter the name : ");
                String name = scStr.nextLine();
                var loggedUser = userService.login(password, name);
                if (loggedUser != null) {
                    userMenu(loggedUser);
                }
            } else if (userInput == 2) {
                System.out.println("Enter the name : ");
                String name = scStr.nextLine();
                System.out.println("Enter the password : ");
                String password = scStr.nextLine();
                User user = new User(name, password);
                var loggedUser = userService.registerUser(user);
                if (loggedUser != null) {
                    userMenu(user);
                }
            }
        }
    }

    private static void userMenu(User loggedUser) throws ExecutionException, InterruptedException {
        while (true) {
            printUserMenu();
            int userIpnut = scInt.nextInt();

            if (userIpnut == 0) {
                break;
            }

            switch (userIpnut) {
                case 1 -> {
                    System.out.println("Enter the card name : ");
                    String name = scStr.nextLine();
                    System.out.println("Enter the card number : ");
                    String cardNumber = scStr.nextLine();
                    var result = StringUtils.weatherTheStringIsOnlyNumber(cardNumber);
                    if ((!result) && cardNumber.length() < 8) {
                        System.out.println("Invalid card number ");
                        break;
                    }
                    System.out.println("Enter the card before date ");
                    String beforeDate = scStr.nextLine();
                    if (!(beforeDate.charAt(2) == '/')) {
                        System.out.println("Invalid before date ");
                        break;
                    }
                    CardTypeEnums.printCardTypes();
                    System.out.println("Enter the card type index : ");
                    int index = scInt.nextInt();
                    var cardType = CardTypeEnums.getTypeByIndex(index);
                    Card card = new Card(name, loggedUser.getId(), cardNumber, beforeDate, cardType);
                    loggedUser.setCard(card);
                    getThreadUserAddCard(card).start();
                }
                case 2 -> {
                    cardService.showUserCards(loggedUser.getId());
                    System.out.print("Enter the card id : ");
                    Integer cardId = scInt.nextInt();
                    System.out.print("Enter the amount of money : ");
                    Double amount = scInt.nextDouble();
                    getThreadUserAddMoney(cardId, loggedUser.getId(), amount).start();
                }
                case 3 -> {
                    getThreadUserShowUserCards(loggedUser.getId()).start();
                }
                case 4 -> {
                    cardService.showUserCards(loggedUser.getId());
                    System.out.print("Enter the card id : ");
                    Integer fromCardId = scInt.nextInt();
                    System.out.print("Enter card number you want to transfer money: ");
                    String cardNumber;
                    do {
                        cardNumber = scStr.nextLine();
                    } while (!StringUtils.weatherTheStringIsOnlyNumber(cardNumber));
                    Double amount;
                    do {
                        System.out.print("Enter amount: ");
                        amount = scInt.nextDouble();
                    } while (amount <= 0);
                    try {
                        var sendMoney = cardService.sendMoney(fromCardId, cardService.getCardByNumber(cardNumber).getId(), amount);
                        if (sendMoney) {
                            TransActionHistory transActionHistory = new TransActionHistory(cardService.getCardById(fromCardId),
                                    cardService.getCardByNumber(cardNumber), amount, StatusEnum.SUCCESS);
                            transActionService.addHistory(transActionHistory);
                            System.out.println("Success");
                        } else {
                            System.out.println("Something went wrong, try again later!");
                        }
                    } catch (Exception e) {
                        TransActionHistory transActionHistory = new TransActionHistory(cardService.getCardById(fromCardId),
                                cardService.getCardByNumber(cardNumber), amount, StatusEnum.FAIL);
                        transActionService.addHistory(transActionHistory);
                        System.out.println(e.getMessage());
                        System.out.println("Something went wrong!");
                    }
                }
                case 5 -> {
                    cardService.showUserCards(loggedUser.getId());
                    System.out.println("What map history do you want to see? ");
                    Integer fromUserCardId = scInt.nextInt();
                    transActionService.showAll(fromUserCardId);
                }
            }
        }
    }

    private static void printUserMenu() {
        System.out.println("""
                1. Add card : 
                2. Add money card                
                3. Show user cards :
                4. Send money :
                5. Show stories : 
                0. Exit :
                """);
    }

    public static Thread getThreadUserAddCard(Card card) {
        return new Thread(() -> {
            cardService.addCard(card);
        });
    }

    public static Thread getThreadUserShowUserCards(Integer userId) {
        return new Thread(() -> {
            cardService.showUserCards(userId);
        });
    }

    public static Thread getThreadUserAddMoney(Integer cardId, Integer ownerId, Double amount) {
        return new Thread(() -> {
            userService.addUserCardMoney(cardId, ownerId, amount);
        });
    }
}