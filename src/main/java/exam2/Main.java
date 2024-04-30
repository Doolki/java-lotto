package exam2;

import exam2.lotto.PurchaseLottoList;
import exam2.lotto.WinningLottoNumber;
import exam2.view.InputView;
import exam2.view.ResultView;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ResultView resultView = new ResultView();
        resultView.purchasePrice();

        InputView inputView = new InputView();
        int price = inputView.purchasePrice();

        PurchaseLottoList purchaseLottoList = new PurchaseLottoList(price);
        int count = purchaseLottoList.getCount();

        resultView.purchaseCount(count);

        resultView.lottoNumberList(purchaseLottoList);

        resultView.winningNumber();

        String winningNumberStr = inputView.winningNumber();

        WinningLottoNumber winningLottoNumber = new WinningLottoNumber(winningNumberStr);

        resultView.winningStatistics();

        purchaseLottoList.lottoEqualList(winningLottoNumber);

        resultView.winningCount(purchaseLottoList);

        resultView.winningRate(purchaseLottoList);
    }
}
