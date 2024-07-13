package exam2.lotto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 구입한 로또 클래스
 */
public class PurchaseTicket {

    private List<LottoNumberRow> lottoNumberRowList = new ArrayList<>();
    private int count = 0;

    private Map<LottoPrize, Integer> matchCount = new HashMap<>();
    private double rate;

    public PurchaseTicket(List<LottoNumberRow> lottoNumberRowList) {
        this.lottoNumberRowList = lottoNumberRowList;
        this.count = lottoNumberRowList.size();
        initMatchCount();
    }

    public static PurchaseTicket createAutoLottoNumber(int price) {
        if (price < LottoPrice.PRICE_1000.getPrice()) {
            throw new IllegalArgumentException("로또 구입 가격은 1000원 이상 이여야 합니다");
        }

        int count = price / LottoPrice.PRICE_1000.getPrice();

        List<LottoNumberRow> lottoNumberRowList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            lottoNumberRowList.add(new LottoNumberRow());
        }

        return new PurchaseTicket(lottoNumberRowList);
    }

    /**
     * 당첨 개수를 업데이트 합니다.
     *
     * @param winningNumber
     */
    public void updateMatchCount(WinningNumber winningNumber) {
        for (LottoNumberRow lottoNumberRow : lottoNumberRowList) {

            LottoPrize lottoPrize = winningNumber.calculateMatchCount(lottoNumberRow);

            matchCount.put(lottoPrize, matchCount.get(lottoPrize) + 1);
        }
    }


    /**
     * 당첨 통계를 계산합니다
     */
    public double getRate() {
        double sum = 0;
        for (LottoPrize key : matchCount.keySet()) {
            sum += matchCount.get(key) * key.getPrize();
        }

        if (sum == 0) {
            return 0;
        }

        return Math.floor((sum / (count * LottoPrice.PRICE_1000.getPrice())) * 100.0) / 100.0;
    }

    public int getCount() {
        return count;
    }

    public List<LottoNumberRow> getLottoNumberRowList() {
        return lottoNumberRowList;
    }

    public Map<LottoPrize, Integer> getMatchCount() {
        return matchCount;
    }

    public void add(LottoNumberRow lottoNumberRow) {
        lottoNumberRowList.add(lottoNumberRow);
        this.count += 1;
    }

    private void initMatchCount() {
        matchCount.put(LottoPrize.ZERO, 0);
        matchCount.put(LottoPrize.FIRST, 0);
        matchCount.put(LottoPrize.SECOND, 0);
        matchCount.put(LottoPrize.THIRD, 0);
        matchCount.put(LottoPrize.FOURTH, 0);
        matchCount.put(LottoPrize.FIFTH, 0);
        matchCount.put(LottoPrize.SIXTH, 0);
        matchCount.put(LottoPrize.SEVENTH, 0);
    }
}
