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
     * 로또 번호의 당첨 개수를 계산 합니다.
     *
     * @param lottoNumberRow
     */
    public LottoPrize calculateMatchCount(LottoNumberRow lottoNumberRow) {

        //당첨 개수 세기
        int count = lottoNumberRow.matchCount(winningNumberRow);

        //보너스 당첨 개수 확인
        int bonus = lottoNumberRow.matchCount(bonusNumber);

        return LottoPrize.getLottoPrize(count, bonus);
    }

    public LottoNumberRow getLottoNumberRow() {
        return winningNumberRow;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}
