package my_click.thread;

import my_click.service.CardService;
import my_click.service.impl.CardServiceImpl;

import java.util.concurrent.Callable;

public class TransferMoney implements Callable<Boolean> {
    private Integer toCardId;
    private Double amount;

    public TransferMoney(Integer toCardId, Double amount) {
        this.toCardId = toCardId;
        this.amount = amount;
    }

    @Override
    public Boolean call() throws Exception {
        try {
            CardService cardService = new CardServiceImpl();
            var cardById = cardService.getCardById(toCardId);
            cardById.setBalance(cardById.getBalance() + amount);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
