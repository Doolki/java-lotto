package domain;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoTicket {

    public static final int TICKET_PRICE = 1_000;

    private final List<LottoNumbers> lottoNumbers;

    public LottoTicket(List<LottoNumbers> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public static LottoTicket buyAutoNumberTicket(int price) {
        if (price < TICKET_PRICE) {
            throw new IllegalArgumentException("티켓을 살수가 없습니다.");
        }

        final List<LottoNumbers> lottoNumbers = new ArrayList<>();

        IntStream.rangeClosed(0, price / TICKET_PRICE)
                .forEach((it) -> lottoNumbers.add(LottoNumbers.generateAutoLottoNumbers()));

        return new LottoTicket(lottoNumbers);
    }

    public List<LottoNumbers> getLottoNumbers() {
        return lottoNumbers;
    }
}
