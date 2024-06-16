package exam2.lotto;

public class LottoService {

    /**
     * 로또 당첨 개수와 비율을 계산합니다.
     */
    public void calculateEqualListAndRate(PurchaseTicket purchaseTicket,
        WinningNumber winningNumber) {
        winningNumber.calculateMatchCount(purchaseTicket);
        purchaseTicket.getRate();
    }

}
