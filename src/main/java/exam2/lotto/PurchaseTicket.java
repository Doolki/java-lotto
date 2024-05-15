package exam2.lotto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 구입한 로또 클래스
 */
public class PurchaseTicket {

    private List<LottoNumbers> lottoNumbersList = new ArrayList<>();
    private int count = 0;

    private Map<LottoPrize, Integer> matchCount = new HashMap<>();
    private double rate;

    public PurchaseTicket(List<LottoNumbers> lottoNumbersList) {
        this.lottoNumbersList = lottoNumbersList;
        this.count = lottoNumbersList.size();
        initMatchCount();
    }

    public static PurchaseTicket createAutoLottoNumber(int price) {
        if (price < LottoPrice.PRICE_1000.getPrice()) {
            throw new IllegalArgumentException("로또 구입 가격은 1000원 이상 이여야 합니다");
        }

        int count = price / LottoPrice.PRICE_1000.getPrice();

        List<LottoNumbers> lottoNumbersList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            lottoNumbersList.add(new LottoNumbers());
        }

        return new PurchaseTicket(lottoNumbersList);
    }

    /**
     * 모든 로또 번호의 당첨 개수를 계산 합니다
     *
     * @param winning
     */
    public void calculateMatchCount(WinningNumber winning) {
        for (int i = 0; i < lottoNumbersList.size(); i++) {

            int count = (int) lottoNumbersList.get(i).getNumberList().stream()
                .filter(lottoNum -> winning.getLottoNumbers().getNumberList().contains(lottoNum))
                .count();

            this.matchCount.put(LottoPrize.getLottoPrize(count),
                this.matchCount.get(LottoPrize.getLottoPrize(count)) + 1);
        }
    }

    /**
     * 당첨 통계를 계산합니다
     */
    public void rate() {
        double sum = 0;
        for (LottoPrize key : matchCount.keySet()) {
            sum += matchCount.get(key) * key.getPrize();
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

    public Map<LottoPrize, Integer> getMatchCount() {
        return matchCount;
    }

    public void add(LottoNumbers lottoNumbers) {
        lottoNumbersList.add(lottoNumbers);
        this.count += 1;
    }

    private void initMatchCount() {
        matchCount.put(LottoPrize.ZERO, 0);
        matchCount.put(LottoPrize.ONE, 0);
        matchCount.put(LottoPrize.TWO, 0);
        matchCount.put(LottoPrize.THREE, 0);
        matchCount.put(LottoPrize.FOUR, 0);
        matchCount.put(LottoPrize.FIVE, 0);
        matchCount.put(LottoPrize.SIX, 0);
    }
}
