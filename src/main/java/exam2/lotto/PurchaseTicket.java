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
     * 모든 로또 번호의 당첨 개수를 계산 합니다
     *
     * @param winning
     */
    public void calculateMatchCount(WinningNumber winning) {

        if (winning.getBonusNumber() == null) {
            throw new NullPointerException("보너스 번호를 넣어주세요");
        }

        for (LottoNumberRow lottoNumberRow : lottoNumberRowList) {

            //당첨 개수 세기
            int count = (int) lottoNumberRow.getNumberList().stream()
                .filter(lottoNum -> winning.getLottoNumbers().checkContainsNumber(lottoNum))
                .count();

            //보너스 당첨 개수 확인
            int bonus = (int) lottoNumberRow.getNumberList().stream()
                .filter(lottoNum -> winning.getBonusNumber().equals(lottoNum))
                .count();

            LottoPrize lottoPrize = LottoPrize.getLottoPrize(count, bonus);

            this.matchCount.put(lottoPrize, this.matchCount.get(lottoPrize) + 1);
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

    public List<LottoNumberRow> getLottoNumbersList() {
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
        matchCount.put(LottoPrize.ONE, 0);
        matchCount.put(LottoPrize.TWO, 0);
        matchCount.put(LottoPrize.THREE, 0);
        matchCount.put(LottoPrize.FOUR, 0);
        matchCount.put(LottoPrize.FIVE, 0);
        matchCount.put(LottoPrize.BONUS, 0);
        matchCount.put(LottoPrize.SIX, 0);
    }
}
