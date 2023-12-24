package my_click.service;

import my_click.domain.Card;

import java.util.concurrent.ExecutionException;

public interface CardService {

    boolean doesTheCardExist(Card card);

    Card getCardById(Integer id);
    Card getCardByNumber(String number);

    String addCard(Card card);

    void showUserCards(Integer ownerId);

    boolean sendMoney(Integer fromCardId, Integer toCardId, Double amount) throws ExecutionException, InterruptedException;
}
