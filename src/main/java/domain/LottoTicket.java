package domain;


import java.util.*;
import java.util.stream.Collectors;

public class LottoTicket {

    public static final int LOTTO_PRICE = 1_000;

    private static final int LOTTO_SIZE = 6;

    private final Set<LottoNumber> lottoNumbers;

    private LottoTicket(Set<LottoNumber> lottoNumbers) {
        if (lottoNumbers == null || lottoNumbers.isEmpty()) {
            throw new IllegalArgumentException("로또 번호는 1개 이상이어야 합니다.");
        }

        if (lottoNumbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호는 " + LOTTO_SIZE + "개 이어야 합니다.");
        }

        this.lottoNumbers = lottoNumbers;
    }

    public static LottoTicket createLottoTicket(Set<LottoNumber> lottoNumbers) {
        return new LottoTicket(lottoNumbers);
    }

    public static LottoTicket createLottoTicket(int... lottoNumbers) {
        if (lottoNumbers == null) {
            throw new IllegalArgumentException("로또 번호는 1개 이상이어야 합니다.");
        }

        return createLottoTicket(Arrays.stream(lottoNumbers)
                .mapToObj(LottoNumber::of)
                .collect(Collectors.toSet())
        );
    }

    public Set<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }
}
