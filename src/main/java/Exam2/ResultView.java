package Exam2;

import java.util.List;
import java.util.Scanner;

public class ResultView {

    public void purchasePrice() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void purchaseCount(int count) {
        System.out.println(count + "개를 구매했습니다.");
    }

    public void lottoNumberList(List<List<Integer>> lottoNumberList) {
        Lotto lotto = new Lotto();

        for (List<Integer> lottoNumber : lottoNumberList) {
            System.out.println(lottoNumber);
        }

    }

    public void winningNumber() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    public void winningStatistics() {
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    public void winningCount(List<Integer> equalCountList) {
        for (int i = 3; i <= 6; i++) {
            System.out.println(
                i + "개 일치 (" + LottoDTO.PRIZE[i] + "원)- " + equalCountList.get(i) + "");
        }
    }

    public void winningRate(double rate) {
        Lotto lotto = new Lotto();
        System.out.print("총 수익률은 " + rate + "입니다.");
        System.out.println(lotto.benefit(rate));
    }

}