package exam2.view;

import exam2.lotto.LottoNumbers;
import exam2.lotto.PurchaseTicket;
import exam2.lotto.LottoPrize;

public class ResultView {

    public void purchasePrice() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void purchaseCount(int count) {
        System.out.println(count + "개를 구매했습니다.");
    }

    public void lottoNumberList(PurchaseTicket purchase) {
        for (LottoNumbers p : purchase.getLottoNumbersList()) {
            System.out.println(p.getNumberList());
        }

    }

    public void winningNumber() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    public void winningCount(PurchaseTicket purchase) {
        System.out.println("당첨 통계");
        System.out.println("---------");

        for (int i = 3; i <= 6; i++) {
            LottoPrize lottoPrize = LottoPrize.getLottoPrize(i);
            System.out.println(
                i + "개 일치 (" + lottoPrize.getPrize() + "원)- "
                    + purchase.getMatchCount().get(lottoPrize) + "");
        }
    }

    public void winningRate(PurchaseTicket purchase) {

        System.out.print("총 수익률은 " + purchase.getRate() + "입니다.");
        System.out.println(benefit(purchase.getRate()));
    }

    //당첨 통계 메세지
    public String benefit(double rate) {
        String benefit = "(기준이 1이기 때문에 결과적으로 손해라는 의미임)";

        if (rate >= 1) {
            benefit = "(기준이 1이기 때문에 결과적으로 이득이라는 의미임)";
        }
        return benefit;
    }

}