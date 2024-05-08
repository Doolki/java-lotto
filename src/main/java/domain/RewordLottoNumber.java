package domain;

public class RewordLottoNumber {

    private final LottoNumbers lottoNumbers;
    private final LottoNumber bonusNumber;

    public RewordLottoNumber(LottoNumbers lottoNumbers, LottoNumber bonusNumber) {
        this.lottoNumbers = lottoNumbers;
        this.bonusNumber = bonusNumber;
    }


    public int matchLottoNumberCount(LottoNumbers lottoNumbers) {
        return this.lottoNumbers.matchCount(lottoNumbers);
    }
}
