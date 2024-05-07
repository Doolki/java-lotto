package exam2.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PurchaseTest {

    @DisplayName("모든 로또 번호 당첨 개수를 계산합니다")
    @Test
    void calculateEqualList() {
        Winning winningNumbers = new Winning("1,2,3,4,5,6");
        Purchase purchase = new Purchase() {{
            add(new LottoNumbers(Arrays.asList(8, 21, 23, 41, 42, 43)));
            add(new LottoNumbers(Arrays.asList(3, 5, 11, 16, 32, 38)));
        }};

        purchase.calculateEqualList(winningNumbers);

        Map<LottoPrize, Integer> expect = new HashMap<>() {{
            put(LottoPrize.ZERO, 1);
            put(LottoPrize.ONE, 0);
            put(LottoPrize.TWO, 1);
            put(LottoPrize.THREE, 0);
            put(LottoPrize.FOUR, 0);
            put(LottoPrize.FIVE, 0);
            put(LottoPrize.SIX, 0);
        }};

        assertThat(purchase.getEqualList()).isEqualTo(expect);
    }

    @DisplayName("수익 비율을 계산합니다")
    @Test
    void rate() {
        Winning winningNumbers = new Winning("1,2,3,4,5,6");
        Purchase purchase = new Purchase() {{
            add(new LottoNumbers(Arrays.asList(8, 21, 23, 41, 42, 43)));
            add(new LottoNumbers(Arrays.asList(3, 5, 11, 16, 32, 38)));
            add(new LottoNumbers(Arrays.asList(7, 11, 16, 35, 36, 44)));
            add(new LottoNumbers(Arrays.asList(1, 8, 11, 31, 41, 42)));
            add(new LottoNumbers(Arrays.asList(13, 14, 16, 38, 42, 45)));
            add(new LottoNumbers(Arrays.asList(7, 11, 30, 40, 42, 43)));
            add(new LottoNumbers(Arrays.asList(2, 13, 22, 32, 38, 45)));
            add(new LottoNumbers(Arrays.asList(23, 25, 33, 36, 39, 41)));
            add(new LottoNumbers(Arrays.asList(1, 3, 5, 14, 22, 45)));
            add(new LottoNumbers(Arrays.asList(5, 9, 38, 41, 43, 44)));
            add(new LottoNumbers(Arrays.asList(2, 8, 9, 18, 19, 21)));
            add(new LottoNumbers(Arrays.asList(13, 14, 18, 21, 23, 35)));
            add(new LottoNumbers(Arrays.asList(17, 21, 29, 37, 42, 45)));
            add(new LottoNumbers(Arrays.asList(3, 8, 27, 30, 35, 44)));
        }};

        purchase.calculateEqualList(winningNumbers); // 당첨 개수 계산
        purchase.rate(); // 수익 비율 계산

        double expect = 0.35;

        assertThat(purchase.getRate()).isEqualTo(expect);
    }

}