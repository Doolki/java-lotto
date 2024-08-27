package exam2;

import exam2.lotto.LottoNumberRow;
import exam2.lotto.LottoService;
import exam2.lotto.PurchaseTicket;
import exam2.lotto.WinningNumber;
import exam2.view.InputView;
import exam2.view.ResultView;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ResultView resultView = new ResultView();
        resultView.purchasePrice();

        InputView inputView = new InputView();
        int price = inputView.purchasePrice();

        resultView.purchaseManualCount();
        int manualCount = inputView.purchaseManualCount();

        resultView.purchaseManualNumber();
        List<LottoNumberRow> lottoNumberRows = inputView.purchaseManualLotto(manualCount);

        PurchaseTicket purchase = PurchaseTicket.createAutoLottoNumber(price, lottoNumberRows);

        resultView.purchaseCount(purchase.getCount());
        resultView.lottoNumberList(purchase);

        resultView.winningNumber();

        LottoNumberRow lottoNumberRow = inputView.winningNumber();

        resultView.bonusNumber();
        int bonusNumber = inputView.bonusNumber();
        WinningNumber winning = new WinningNumber(lottoNumberRow, bonusNumber);

        LottoService lottoService = new LottoService();
        lottoService.calculateEqualListAndRate(purchase, winning);

        resultView.winningCount(purchase);
        resultView.winningRate(purchase);
    }
}
