package my_click.service;

import my_click.domain.Card;
import my_click.domain.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public interface UserService {

    List<User> USERS = Collections.synchronizedList(new ArrayList<>());
    List<Card> USER_CARDS = Collections.synchronizedList(new LinkedList<>());

    User login(String password, String name);

    String registerUser(User user);

    void addUserCardMoney(Integer cardIndex, Integer ownerId, Double amount);

    void showUsers(Integer userId);
    
    boolean doesThePersonHave_A_Card(Integer toUserId);

    User getUserById(Integer userId);
}
