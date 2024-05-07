package exam2.lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
//구입한 로또 클래스
public class Purchase {

    private List<LottoNumbers> lottoNumbersList = new ArrayList<>();
    private int count = 0;

    private Map<LottoPrize, Integer> equalList = new HashMap<>() {{
        put(LottoPrize.ZERO, 0);
        put(LottoPrize.ONE, 0);
        put(LottoPrize.TWO, 0);
        put(LottoPrize.THREE, 0);
        put(LottoPrize.FOUR, 0);
        put(LottoPrize.FIVE, 0);
        put(LottoPrize.SIX, 0);
    }};
    private double rate;

    public Purchase() {
    }

    public Purchase(int price) {
        if (price < LottoPrice.PRICE_1000.getPrice()) {
            throw new IllegalArgumentException("로또 구입 가격은 1000원 이상 이여야 합니다");
        }

        this.count = (int) (price / LottoPrice.PRICE_1000.getPrice());

        for (int i = 0; i < count; i++) {
            lottoNumbersList.add(new LottoNumbers());
        }
    }

    /**
     * 모든 로또 번호의 당첨 개수를 계산합니다
     *
     * @param winning
     */
    public void calculateEqualList(Winning winning) {
        for (int i = 0; i < lottoNumbersList.size(); i++) {

            int count = (int) lottoNumbersList.get(i).getNumberList().stream()
                .filter(lottoNum -> winning.getLottoNumbers().getNumberList().contains(lottoNum))
                .count();

            this.equalList.put(LottoPrize.getLottoPrize(count),
                this.equalList.get(LottoPrize.getLottoPrize(count)) + 1);
        }
    }

    /**
     * 당첨 통계를 계산합니다
     */
    public void rate() {
        double sum = 0;
        for (LottoPrize key : equalList.keySet()) {
            sum += equalList.get(key) * key.getPrize();
        }

        if (sum == 0) {
            rate = 0;
            return;
        }

        this.rate = Math.floor((sum / (count * LottoPrice.PRICE_1000.getPrice())) * 100.0) / 100.0;
    }

    public int getCount() {
        return count;
    }

    public double getRate() {
        return rate;
    }

    public List<LottoNumbers> getLottoNumbersList() {
        return lottoNumbersList;
    }

    public Map<LottoPrize, Integer> getEqualList() {
        return equalList;
    }

    public void add(LottoNumbers lottoNumbers) {
        lottoNumbersList.add(lottoNumbers);
        this.count += 1;
    }
}
