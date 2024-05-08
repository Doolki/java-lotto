package domain;

import java.util.Map;

public class LottoReturnCalculator {
    public double calculate(Map<LottoReword, Integer> rewords) {
        int totCount = 0;
        int totReword = 0;

        for (Map.Entry<LottoReword, Integer> lottoRewordIntegerEntry : rewords.entrySet()) {
            totCount += lottoRewordIntegerEntry.getValue();
            totReword += lottoRewordIntegerEntry.getKey().getReword() * lottoRewordIntegerEntry.getValue();
        }

        final int totalPrice = totCount * LottoTicket.TICKET_PRICE;
        final int totalReword = totReword;

        return (double) totalReword / totalPrice;
    }
}
