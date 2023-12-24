package my_click.enums;

public enum CardTypeEnums {

    VISA_CLASSIC(1),
    VISA_GOLD(2),
    MASTERCARD_AVIASALES(3),
    MASTERCARD_GOLD(4),
    MASTERCARD_STANDARD(5),
    VISA_PLATINUM(6),
    HUMO(7),
    UZ_CARD(8);

    private Integer index = 0;

    CardTypeEnums(Integer index) {
        this.index = index;
    }

    public static void printCardTypes() {
        for (CardTypeEnums cardTypeEnums : CardTypeEnums.values()) {
            System.out.println(cardTypeEnums + " : " + cardTypeEnums.index);
        }
    }

    public static CardTypeEnums getTypeByIndex(Integer index) {
        for (CardTypeEnums cardTypeEnums : CardTypeEnums.values()) {
            if (cardTypeEnums.index.equals(index)){
                return cardTypeEnums;
            }
        }
        return HUMO;
    }
}
