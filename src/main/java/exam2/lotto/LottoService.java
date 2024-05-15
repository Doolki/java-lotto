package exam2.lotto;

public class LottoService {

    private PurchaseTicket purchase;

    private WinningNumber winning;

    public LottoService(PurchaseTicket purchase, WinningNumber winning) {
        this.purchase = purchase;
        this.winning = winning;
    }

    /**
     * 로또 당첨 개수와 비율을 계산합니다.
     */
    public void calculateEqualListAndRate() {
        purchase.calculateMatchCount(winning);
        purchase.rate();
    }

}
