package domain;


import java.util.*;
import java.util.stream.Collectors;

public class LottoTicket {

    public static final int LOTTO_PRICE = 1_000;

    private static final int LOTTO_SIZE = 6;

    private final LottoNumbers lottoNumbers;

    private LottoTicket(LottoNumbers lottoNumbers) {
        if (lottoNumbers == null) {
            throw new IllegalArgumentException("로또 번호는 비어 있으면 안됩니다.");
        }

        if (lottoNumbers.hasNotSize(LOTTO_SIZE)) {
            throw new IllegalArgumentException("로또 번호는 " + LOTTO_SIZE + "개 이어야 합니다.");
        }

        this.lottoNumbers = lottoNumbers;
    }

    public static LottoTicket createLottoTicket(Set<LottoNumber> lottoNumbers) {
        return new LottoTicket(LottoNumbers.of(lottoNumbers));
    }

    public static LottoTicket createLottoTicket(LottoNumbers lottoNumbers) {
        return new LottoTicket(lottoNumbers);
    }

}
