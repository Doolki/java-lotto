package exam2.lotto;

//당첨 로또 클래스
public class WinningNumber {

    private LottoNumbers lottoNumbers;

    public WinningNumber(String lottoStr) {
        this.lottoNumbers = new LottoNumbers(lottoStr);
    }

    public LottoNumbers getLottoNumbers() {
        return lottoNumbers;
    }
}
