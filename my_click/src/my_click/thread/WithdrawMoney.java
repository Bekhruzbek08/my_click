package my_click.thread;

import my_click.domain.Card;
import my_click.service.CardService;
import my_click.service.impl.CardServiceImpl;

import java.util.concurrent.Callable;

public class WithdrawMoney implements Callable<Boolean> {
    private Integer fromCard;
    private Double amount;

    public WithdrawMoney(Integer fromCard, Double amount) {
        this.fromCard = fromCard;
        this.amount = amount;
    }

    @Override
    public Boolean call() throws Exception {
        try {
            CardService cardService = new CardServiceImpl();
            var cardById = cardService.getCardById(fromCard);
            if (cardById.getBalance() <= amount ) {
                return false;
            }
            cardById.setBalance(cardById.getBalance()-amount);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
