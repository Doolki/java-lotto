package exam2.lotto;

//당첨 로또 클래스
public class WinningNumber {

    private LottoNumberRow winningNumbers;

    private LottoNumber bonusNumber;

    public WinningNumber(String lottoStr, int bonusNumber) {
        this.winningNumbers = new LottoNumberRow(lottoStr);

        LottoNumber bonusLottoNumber = new LottoNumber(bonusNumber);

        if (winningNumbers.checkContainsNumber(bonusLottoNumber)) {
            throw new IllegalArgumentException("당첨 번호와 보너스 번호는 중복될 수 없습니다.");
        }

        this.bonusNumber = bonusLottoNumber;
    }

    public LottoNumberRow getLottoNumbers() {
        return winningNumbers;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}
