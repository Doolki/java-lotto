package exam2.view;

import java.util.Scanner;

public class InputView {

    public int purchasePrice() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public String winningNumber() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

}
