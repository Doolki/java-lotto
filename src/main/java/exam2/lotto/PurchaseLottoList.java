package exam2.lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//로또 구입 번호 클래스
public class PurchaseLottoList {

    private final List<List<Integer>> numberList = new ArrayList<>();
    private final static int lottoStartNumber = 1;
    private final static int lottoEndNumber = 45;
    private int count;
    private List<Integer> equalList;
    private double rate;

    public PurchaseLottoList() {
    }

    //로또 구입 시 가격에 맞는 랜덤 로또 번호를 생성합니다.
    public PurchaseLottoList(int price) {
        if (price < LottoPrice.PRICE_1000.getPrice()) {
            throw new IllegalArgumentException("로또 구입 가격은 1000원 이상 이여야 합니다");
        }

        this.count = (int) (price / LottoPrice.PRICE_1000.getPrice());

        for (int i = 0; i < this.count; i++) {
            this.add(makeLottoNumber());
        }
    }

    public List<List<Integer>> getNumberList() {
        return numberList;
    }

    public List<Integer> getNumbers(int index) {
        return numberList.get(index);
    }

    public int getCount() {
        return count;
    }

    public double getRate() {
        return rate;
    }

    public List<Integer> getEqualList() {
        return equalList;
    }

    public void add(List<Integer> number) {
        numberList.add(number);
    }

    //로또 랜덤 번호를 생성합니다
    public List<Integer> makeLottoNumber() {
        List<Integer> list = new ArrayList<>();
        for (int i = lottoStartNumber; i <= lottoEndNumber; i++) {
            list.add(i);
        }

        Collections.shuffle(list);
        List<Integer> lottoNumberList = list.subList(0, 6);
        Collections.sort(lottoNumberList);

        return lottoNumberList;
    }

    //모든 로또 번호의 당첨 개수를 계산하고 수익률을 계산합니다
    public void lottoEqualList(WinningLottoNumber winningLottoNumber) {
        equalList = Arrays.asList(0, 0, 0, 0, 0, 0, 0);

        for (List<Integer> list : numberList) {
            int index = equalCount(list, winningLottoNumber);
            equalList.set(index, equalList.get(index) + 1);
        }

        this.rate();
    }

    //로또 당첨 개수를 계산합니다
    public int equalCount(List<Integer> list, WinningLottoNumber winningLottoNumber) {
        int equalCount = 0;
        for (Integer lottoNum : list) {
            equalCount += isEqual(lottoNum, winningLottoNumber);
        }
        return equalCount;
    }

    //로또 번호 1개 당첨 여부를 확인합니다
    public int isEqual(int lottoNum, WinningLottoNumber winningLottoNumber) {
        int equal = 0;
        if (winningLottoNumber.getWinningNumberList().contains(lottoNum)) {
            equal = 1;
        }
        return equal;
    }

    //당첨 통계를 계산합니다
    public void rate() {
        double sum = 0;
        for (int i = 0; i < equalList.size(); i++) {
            sum += equalList.get(i) * LottoPrize.PRIZE.getPrize()[i];
        }

        if (sum == 0) {
            rate = 0;
            return;
        }

        this.rate = Math.floor((sum / (count * LottoPrice.PRICE_1000.getPrice())) * 100.0) / 100.0;
    }

}
