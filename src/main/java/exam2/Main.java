package exam2;

import exam2.lotto.LottoService;
import exam2.lotto.PurchaseTicket;
import exam2.lotto.WinningNumber;
import exam2.view.InputView;
import exam2.view.ResultView;

public class Main {

    public static void main(String[] args) {
        ResultView resultView = new ResultView();
        resultView.purchasePrice();

        InputView inputView = new InputView();
        int price = inputView.purchasePrice();

        PurchaseTicket purchase = PurchaseTicket.createAutoLottoNumber(price);

        resultView.purchaseCount(purchase.getCount());
        resultView.lottoNumberList(purchase);

        resultView.winningNumber();

        String winningNumberStr = inputView.winningNumber();

        WinningNumber winning = new WinningNumber(winningNumberStr);

        LottoService lottoService = new LottoService(purchase, winning);
        lottoService.calculateEqualListAndRate();

        resultView.winningCount(purchase);
        resultView.winningRate(purchase);
    }
}
