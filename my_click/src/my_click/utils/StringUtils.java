package my_click.utils;

public class StringUtils {
    public static boolean weatherTheStringIsOnlyNumber(String cardNumber) {
        cardNumber = cardNumber.replace(" ", "");
        for (int i = 0; i < cardNumber.length(); i++) {
            if (!Character.isDigit(cardNumber.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
