package my_click.service;

import my_click.domain.Card;
import my_click.domain.TransActionHistory;

import java.util.*;

public interface TransActionService {
    Set<TransActionHistory> TRANS_ACTION_HISTORY_LIST = Collections.synchronizedSet(new TreeSet<>());
    void showAll(Integer fromUserCardId);

    void addHistory(TransActionHistory transActionHistory);
}
