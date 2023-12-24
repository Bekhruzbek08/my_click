package my_click.service.impl;

import my_click.domain.Card;
import my_click.enums.CardTypeEnums;
import my_click.service.CardService;
import my_click.thread.TransferMoney;
import my_click.thread.WithdrawMoney;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

import static my_click.service.UserService.USER_CARDS;

public class CardServiceImpl implements CardService {
    {
        addCard(new Card("main", 1, "12341234", "12/24", CardTypeEnums.UZ_CARD));
        addCard(new Card("pdp", 1, "00000000", "12/24", CardTypeEnums.VISA_PLATINUM));
        addCard(new Card("uni", 2, "12345678", "12/90", CardTypeEnums.VISA_PLATINUM));
    }

    @Override
    public String addCard(Card card) {
        if (doesTheCardExist(card)) {
            return null;
        }
        USER_CARDS.add(card);
        return "Successfully";
    }

    @Override
    public void showUserCards(Integer ownerId) {
        for (Card card : USER_CARDS) {
            if (card.getOwnerId().equals(ownerId)) {
                System.out.println(card);
            }
        }
    }

    @Override
    public boolean doesTheCardExist(Card card) {
        for (Card card1 : USER_CARDS) {
            if (card1.getName().equals(card.getName())) {
                return true;
            } else if (card1.getCardNumber().equals(card.getCardNumber())) {
                System.out.println("A card with this number exists !" + card1.getCardNumber());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean sendMoney(Integer fromCardId, Integer toCardId, Double amount) throws ExecutionException, InterruptedException {
        var executorService = Executors.newFixedThreadPool(5);
        if (!doesTheCardExist(getCardById(toCardId))) {
            return false;
        }
        var withDrawResponse = executorService.submit(new WithdrawMoney(fromCardId, amount));
        if (withDrawResponse.get().equals(true)) {
            return executorService.submit(new TransferMoney(toCardId, amount)).get();
        }
        return false;
    }

    @Override
    public Card getCardById(Integer id) {
        for (Card card : USER_CARDS) {
            if (card.getId().equals(id)) {
                return card;
            }
        }
        return null;
    }

    @Override
    public Card getCardByNumber(String number) {
        for (Card card : USER_CARDS) {
            if (card.getCardNumber().equals(number)) {
                return card;
            }
        }
        return null;
    }
}
