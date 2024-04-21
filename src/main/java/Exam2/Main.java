package Exam2;

public class Main {
    public static void main(String[] args) {
        ResultView resultView = new ResultView();
        resultView.purchasePrice();

        InputView inputView = new InputView();
        int price = inputView.purchasePrice();

        Lotto lotto = new Lotto();
        int count = lotto.purchaseCount(price);

        resultView.purchaseCount(count);



    }
}
