import domain.*;
import view.InputView;
import view.ResultView;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        // 지불금액 입력
        int userPrice = InputView.userPriceInput();

        final LottoTicket userLottoTicket = LottoTicket.buyAutoNumberTicket(userPrice);

        ResultView.ticketInfo(userLottoTicket);

        final String[] winnerNumberInput = InputView.winnerNumberInput();
        // 당첨 번호 출력
        LottoNumbers winnerLottoNumbers = LottoNumbers.of(winnerNumberInput);
        ResultView.printLottoNumbers(winnerLottoNumbers.getValues());

        Map<LottoReword, Integer> lottoRewordIntegerMap = LottoReword.checkLottoRewords(userLottoTicket, winnerLottoNumbers);

        LottoReturnCalculator lottoReturnCalculator = new LottoReturnCalculator();
        double calculate = lottoReturnCalculator.calculate(lottoRewordIntegerMap);

        System.out.println(calculate);
    }
}
