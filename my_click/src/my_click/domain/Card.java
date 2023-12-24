package my_click.domain;

import my_click.enums.CardTypeEnums;

public class Card {


    private static Integer sequence = 0;

    {
        sequence++;
    }

    private Integer id = sequence;
    private String name;
    private Integer ownerId;
    private Double balance;
    private String cardNumber;
    private String bestBeforeDate;
    private CardTypeEnums cardTypeEnums;

    public Card() {
    }

    public Card(String name, Integer ownerId, String cardNumber, String bestBeforeDate, CardTypeEnums cardTypeEnums) {
        this.name = name;
        this.ownerId = ownerId;
        this.cardNumber = cardNumber;
        this.bestBeforeDate = bestBeforeDate;
        this.cardTypeEnums = cardTypeEnums;
    }

    public CardTypeEnums getCardTypeEnums() {
        return cardTypeEnums;
    }

    public void setCardTypeEnums(CardTypeEnums cardTypeEnums) {
        this.cardTypeEnums = cardTypeEnums;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Double getBalance() {
        return balance == null ? 0 : balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getBestBeforeDate() {
        return bestBeforeDate;
    }

    public void setBestBeforeDate(String bestBeforeDate) {
        this.bestBeforeDate = bestBeforeDate;
    }


    @Override
    public String toString() {
        return String.format("Name = %s, Card number = %s, Best before date = %s, balance = %s, Id = %s , Type = %s", name, cardNumber, bestBeforeDate, (balance == null) ? "no cash " : balance, id, cardTypeEnums);
    }
}
