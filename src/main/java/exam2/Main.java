package exam2;

import exam2.lotto.Purchase;
import exam2.lotto.Winning;
import exam2.view.InputView;
import exam2.view.ResultView;

public class Main {

    public static void main(String[] args) {
        ResultView resultView = new ResultView();
        resultView.purchasePrice();

        InputView inputView = new InputView();
        int price = inputView.purchasePrice();

        Purchase purchase = new Purchase(price);
        int count = purchase.getCount();

        resultView.purchaseCount(count);

        resultView.lottoNumberList(purchase);

        resultView.winningNumber();

        String winningNumberStr = inputView.winningNumber();

        Winning winning = new Winning(winningNumberStr);

        resultView.winningStatistics();

        purchase.lottoEqualList(winning);

        resultView.winningCount(purchase);

        resultView.winningRate(purchase);
    }
}
