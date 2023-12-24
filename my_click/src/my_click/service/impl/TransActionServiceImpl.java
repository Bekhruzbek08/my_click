package my_click.service.impl;

import my_click.domain.TransActionHistory;
import my_click.service.TransActionService;
import my_click.utils.DateUtils;


public class TransActionServiceImpl implements TransActionService {
    @Override
    public void showAll(Integer userCardId) {
        for (TransActionHistory actionHistory : TRANS_ACTION_HISTORY_LIST) {
            if (actionHistory.getFromCard() != null && actionHistory.getFromCard().getId().equals(userCardId)) {
                System.out.println(actionHistory.getToCard().getName() + " " + actionHistory.getToCard().getCardNumber() +
                        " -" + actionHistory.getAmount() + " " + DateUtils.getBeautifulDate(actionHistory.getLocalDateTime()));
            } else if (actionHistory.getToCard() != null && actionHistory.getToCard().getId().equals(userCardId)) {
                System.out.println(actionHistory.getFromCard().getName() + " " + actionHistory.getFromCard().getCardNumber() +
                        " +" + actionHistory.getAmount() + " " + DateUtils.getBeautifulDate(actionHistory.getLocalDateTime()));
            }
        }
    }

    @Override
    public void addHistory(TransActionHistory transActionHistory) {
        TRANS_ACTION_HISTORY_LIST.add(transActionHistory);
    }
}
