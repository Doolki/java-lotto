package domain;

import java.util.*;
import java.util.stream.Collectors;

public enum LottoReword {

    FIRST_PLACE(6, 5_000),
    SECOND_PLACE(5, 2_000_000_000),
    THIRD_PLACE(4, 50_000),
    FOURTH_PLACE(3, 5_000),
    NONE(-1, 0);

    private final static Map<Integer, LottoReword> countMap = Arrays.stream(LottoReword.values())
            .collect(Collectors.toMap(LottoReword::getMatchLottoNumberCount, lottoReword -> lottoReword));

    private final int matchLottoNumberCount;
    private final int reword;


    LottoReword(int matchLottoNumberCount, int reword) {
        this.matchLottoNumberCount = matchLottoNumberCount;
        this.reword = reword;
    }

    public static Map<LottoReword, Integer> checkLottoRewords(LottoTicket lottoTicket, LottoNumbers rewordLottoNumber) {
        Map<LottoReword, Integer> lottoRewordMap = new EnumMap<>(LottoReword.class);

        List<LottoNumbers> ticketLottoNumbers = lottoTicket.getLottoNumbers();

        for (LottoNumbers lottoNumber : ticketLottoNumbers) {
            LottoReword lottoReword = countByLottoReword(rewordLottoNumber.matchCount(lottoNumber));
            lottoRewordMap.put(lottoReword, lottoRewordMap.getOrDefault(lottoReword, 0) + 1);
        }

        return lottoRewordMap;
    }

    private static LottoReword countByLottoReword(int matchLottoNumberCount) {
        return countMap.getOrDefault(matchLottoNumberCount, LottoReword.NONE);
    }


    public int getMatchLottoNumberCount() {
        return matchLottoNumberCount;
    }

    public int getReword() {
        return reword;
    }
}
