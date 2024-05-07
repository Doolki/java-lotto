package exam2.lotto;

public class LottoService {

    private Purchase purchase;

    private Winning winning;

    public LottoService(Purchase purchase, Winning winning) {
        this.purchase = purchase;
        this.winning = winning;
    }

    /**
     * 로또 당첨 개수와 비율을 계산합니다.
     */
    public void calculateEqualListAndRate() {
        purchase.calculateEqualList(winning);
        purchase.rate();
    }

}
