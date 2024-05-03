package exam2.lotto;

import java.util.Arrays;
import java.util.List;

//구입한 로또 클래스
public class Purchase {

    private LottoNumbers lottoNumbers = new LottoNumbers();
    private int count = 0;
    private List<Integer> equalList;
    private double rate;

    public Purchase() {
    }

    public Purchase(int price) {
        if (price < LottoPrice.PRICE_1000.getPrice()) {
            throw new IllegalArgumentException("로또 구입 가격은 1000원 이상 이여야 합니다");
        }

        this.count = (int) (price / LottoPrice.PRICE_1000.getPrice());

        lottoNumbers = new LottoNumbers(count);
    }

    //모든 로또 번호의 당첨 개수를 계산하고 수익률을 계산합니다
    public void lottoEqualList(Winning winning) {
        equalList = Arrays.asList(0, 0, 0, 0, 0, 0, 0);

        for (List<Integer> list : lottoNumbers.getNumberList()) {
            int index = equalCount(list, winning);
            equalList.set(index, equalList.get(index) + 1);
        }

        this.rate();
    }

    //로또 당첨 개수를 계산합니다
    public int equalCount(List<Integer> list, Winning winning) {
        int equalCount = 0;
        for (Integer lottoNum : list) {
            equalCount += isEqual(lottoNum, winning);
        }
        return equalCount;
    }

    //로또 번호 1개 당첨 여부를 확인합니다
    public int isEqual(int lottoNum, Winning winning) {
        int equal = 0;
        if (winning.getLottoNumbers().getNumberList().get(0).contains(lottoNum)) {
            equal = 1;
        }
        return equal;
    }

    //당첨 통계를 계산합니다
    public void rate() {
        double sum = 0;
        for (int i = 0; i < equalList.size(); i++) {
            sum += equalList.get(i) * LottoPrize.values()[i].getPrize();
        }

        if (sum == 0) {
            rate = 0;
            return;
        }

        this.rate = Math.floor((sum / (count * LottoPrice.PRICE_1000.getPrice())) * 100.0) / 100.0;
    }

    public void add(List<Integer> list) {
        lottoNumbers.add(list);
        count += 1;
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

    public LottoNumbers getLottoNumbers() {
        return lottoNumbers;
    }

}
