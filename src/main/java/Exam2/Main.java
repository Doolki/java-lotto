package Exam2;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ResultView resultView = new ResultView();
        resultView.purchasePrice();

        InputView inputView = new InputView();
        int price = inputView.purchasePrice();

        Lotto lotto = new Lotto();
        int count = lotto.purchaseCount(price);

        resultView.purchaseCount(count);

        List<List<Integer>> lottoNumberList = lotto.makeLottoNumberList(count);

        resultView.lottoNumberList(lottoNumberList);

        resultView.winningNumber();

        String winningNumberStr = inputView.winningNumber();

        List<Integer> winningNumberList = lotto.makeWinningNumber(winningNumberStr);

        resultView.winningStatistics();

        List<Integer> equalCountList = lotto.lottoEqualList(lottoNumberList, winningNumberList);

        resultView.winningCount(equalCountList);

        double rate = lotto.rate(count, equalCountList);
        resultView.winningRate(rate);


    }
}
