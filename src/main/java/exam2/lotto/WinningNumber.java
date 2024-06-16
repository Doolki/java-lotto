package exam2.lotto;

//당첨 로또 클래스
public class WinningNumber {

    private LottoNumberRow winningNumberRow;

    private LottoNumber bonusNumber;

    public WinningNumber(String lottoStr, int bonusNumber) {
        winningNumberRow = new LottoNumberRow(lottoStr);

        LottoNumber bonusLottoNumber = new LottoNumber(bonusNumber);

        if (winningNumberRow.checkContainsNumber(bonusLottoNumber)) {
            throw new IllegalArgumentException("당첨 번호와 보너스 번호는 중복될 수 없습니다.");
        }

        this.bonusNumber = bonusLottoNumber;
    }

    /**
     * 모든 로또 번호의 당첨 개수를 계산 합니다
     *
     * @param purchaseTicket
     */
    public void calculateMatchCount(PurchaseTicket purchaseTicket) {

        if (bonusNumber == null) {
            throw new NullPointerException("보너스 번호를 넣어주세요");
        }

        for (LottoNumberRow lottoNumberRow : purchaseTicket.getLottoNumberRowList()) {

            //당첨 개수 세기
            int count = (int) lottoNumberRow.getNumberList().stream()
                .filter(lottoNum -> winningNumberRow.checkContainsNumber(lottoNum))
                .count();

            //보너스 당첨 개수 확인
            int bonus = (int) lottoNumberRow.getNumberList().stream()
                .filter(lottoNum -> bonusNumber.equals(lottoNum))
                .count();

            LottoPrize lottoPrize = LottoPrize.getLottoPrize(count, bonus);

            purchaseTicket.getMatchCount()
                .put(lottoPrize, purchaseTicket.getMatchCount().get(lottoPrize) + 1);
        }
    }

    public LottoNumberRow getLottoNumberRow() {
        return winningNumberRow;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}
