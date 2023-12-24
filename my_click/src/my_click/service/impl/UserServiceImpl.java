package my_click.service.impl;

import my_click.domain.Card;
import my_click.domain.User;
import my_click.service.UserService;

public class UserServiceImpl implements UserService {

    {
        USERS.add(new User("Rock", "r001"));
        USERS.add(new User("Jek", "r002"));
        USERS.add(new User("John", "r003"));
        USERS.add(new User("Piter", "r004"));
    }

    @Override
    public User login(String password, String name) {
        for (User user : USERS) {
            if (user.getPassword().equals(password) && user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public String registerUser(User user) {
        if (doesTheUserExist(user)) {
            return null;
        }
        USERS.add(user);
        return "Successfully";
    }

    @Override
    public void addUserCardMoney(Integer cardId, Integer ownerId, Double amount) {
        for (Card card : USER_CARDS) {
            if (card.getId().equals(cardId) && card.getOwnerId().equals(ownerId)) {
                var res = (card.getBalance() + amount);
                card.setBalance(res);
                break;
            }
        }
    }

    @Override
    public void showUsers(Integer userId) {
        for (User user : USERS) {
            if (!user.getId().equals(userId)) {
                System.out.println(user);
            }
        }
    }

    @Override
    public boolean doesThePersonHave_A_Card(Integer toUserId) {
        var userById = getUserById(toUserId);
        if (userById != null) {
            if (userById.getCard() == null) {
                return false;
            }
        }
        return true;
    }


    public User getUserById(Integer userId) {
        for (User user : USERS) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    private boolean doesTheUserExist(User user) {
        for (User user1 : USERS) {
            if (user1.getName().equals(user.getName())) {
                System.out.println("A user with this name exists !" + user1.getName());
                return true;
            } else if (user1.getPassword().equals(user.getPassword())) {
                System.out.println("A user with this password exists !" + user1.getPassword());
                return true;
            }
        }
        return false;
    }
}
