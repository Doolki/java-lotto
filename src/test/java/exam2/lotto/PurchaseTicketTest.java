package exam2.lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PurchaseTicketTest {

    @DisplayName("모든 로또 번호 당첨 개수를 계산합니다")
    @Test
    void 모든_로또_번호_당첨_개수를_계산합니다() {
        WinningNumber winningNumbers = new WinningNumber("1,2,3,4,5,6");
        winningNumbers.makeBonusNumber(40);

        List<LottoNumberRow> lottoNumberList = new ArrayList<>();

        lottoNumberList.add(new LottoNumberRow(Arrays.asList(8, 21, 23, 41, 42, 43)));
        lottoNumberList.add(new LottoNumberRow(Arrays.asList(3, 5, 11, 16, 32, 38)));
        lottoNumberList.add(new LottoNumberRow(Arrays.asList(1, 2, 3, 4, 5, 40)));

        PurchaseTicket purchase = new PurchaseTicket(lottoNumberList);

        purchase.calculateMatchCount(winningNumbers);

        Map<LottoPrize, Integer> expect = new HashMap<>() {{
            put(LottoPrize.ZERO, 1);
            put(LottoPrize.ONE, 0);
            put(LottoPrize.TWO, 1);
            put(LottoPrize.THREE, 0);
            put(LottoPrize.FOUR, 0);
            put(LottoPrize.FIVE, 0);
            put(LottoPrize.BONUS, 1);
            put(LottoPrize.SIX, 0);
        }};

        assertThat(purchase.getMatchCount()).isEqualTo(expect);
    }

    @DisplayName("수익 비율을 계산합니다")
    @Test
    void 수익_비율을_계산합니다() {
        WinningNumber winningNumbers = new WinningNumber("1,2,3,4,5,6");
        winningNumbers.makeBonusNumber(39);

        List<LottoNumberRow> lottoNumberList = new ArrayList<>();

        lottoNumberList.add(new LottoNumberRow(Arrays.asList(8, 21, 23, 41, 42, 43)));
        lottoNumberList.add(new LottoNumberRow(Arrays.asList(3, 5, 11, 16, 32, 38)));
        lottoNumberList.add(new LottoNumberRow(Arrays.asList(7, 11, 16, 35, 36, 44)));
        lottoNumberList.add(new LottoNumberRow(Arrays.asList(1, 8, 11, 31, 41, 42)));
        lottoNumberList.add(new LottoNumberRow(Arrays.asList(13, 14, 16, 38, 42, 45)));
        lottoNumberList.add(new LottoNumberRow(Arrays.asList(7, 11, 30, 40, 42, 43)));
        lottoNumberList.add(new LottoNumberRow(Arrays.asList(2, 13, 22, 32, 38, 45)));
        lottoNumberList.add(new LottoNumberRow(Arrays.asList(23, 25, 33, 36, 39, 41)));
        lottoNumberList.add(new LottoNumberRow(Arrays.asList(1, 3, 5, 14, 22, 45)));
        lottoNumberList.add(new LottoNumberRow(Arrays.asList(5, 9, 38, 41, 43, 44)));
        lottoNumberList.add(new LottoNumberRow(Arrays.asList(2, 8, 9, 18, 19, 21)));
        lottoNumberList.add(new LottoNumberRow(Arrays.asList(13, 14, 18, 21, 23, 35)));
        lottoNumberList.add(new LottoNumberRow(Arrays.asList(17, 21, 29, 37, 42, 45)));
        lottoNumberList.add(new LottoNumberRow(Arrays.asList(3, 8, 27, 30, 35, 44)));

        PurchaseTicket purchase = new PurchaseTicket(lottoNumberList);

        purchase.calculateMatchCount(winningNumbers); // 당첨 개수 계산
        purchase.getRate(); // 수익 비율 계산

        double expect = 0.35;

        assertThat(purchase.getRate()).isEqualTo(expect);
    }

}