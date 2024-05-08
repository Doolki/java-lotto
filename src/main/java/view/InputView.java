package view;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputView {
    private static final String NUMBER_SEPARATOR = ",";
    private static final String INPUT_BUY_PRICE = "구입금액을 입력해 주세요.";
    private static final String INPUT_RETRY_BUY_PRICE = "로또의 구매금액이 모잘릅니다. 재 입력 해주세요.";
    private static final String INPUT_WINNER_NUMBER = "지난 주 당첨 번호를 입력해주세요.";
    private static final String INPUT_BONUS_NUMBER = "보너스 번호를 입력해주세요.";

    private static final Pattern WINNER_NUMBER_FORMAT = Pattern.compile("\\d,*");


    public static int userPriceInput() {
        System.out.println(INPUT_BUY_PRICE);
        return new Scanner(System.in).nextInt();
    }

    public static int retryPriceInput() {
        System.out.println(INPUT_RETRY_BUY_PRICE);
        return new Scanner(System.in).nextInt();
    }

    public static int bonusNumberInput() {
        System.out.println(INPUT_BONUS_NUMBER);
        return new Scanner(System.in).nextInt();
    }

    public static String[] winnerNumberInput() {
        System.out.println(INPUT_WINNER_NUMBER);
        String winnerText = new Scanner(System.in).nextLine().replaceAll("\\s", "");

        Matcher matcher = WINNER_NUMBER_FORMAT.matcher(winnerText);
        if (!matcher.find()) {
            throw new IllegalArgumentException("지원하는 포멧이 아닙니다.");
        }

        return winnerText.split(",");
    }
}

