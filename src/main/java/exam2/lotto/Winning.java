package exam2.lotto;

//당첨 로또 클래스
public class Winning {

    private LottoNumbers lottoNumbers;

    public Winning() {
    }

    public Winning(String lottoStr) {
        this.lottoNumbers = new LottoNumbers(lottoStr);
    }

    public LottoNumbers getLottoNumbers() {
        return lottoNumbers;
    }
}
