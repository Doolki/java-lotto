package Exam2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class LottoTest {
    @DisplayName("로또 구입 가격 0 예외 처리")
    @ParameterizedTest
    @ValueSource(ints = {0})
    void purchasePriceException(int input) {
        Lotto lotto = new Lotto();

        assertThatThrownBy(() -> {
            lotto.purchaseCount(input);
        }).isInstanceOf(RuntimeException.class)
                .hasMessage("로또 구입 가격은 0이 될 수 없습니다.");

    }
    @DisplayName("로또 구입 개수 계산")
    @ParameterizedTest
    @CsvSource(value = {"1000:1", "1113455:1113", "12000:12", "12500:12"}, delimiter = ':')
    void purchaseCount(int input, int expect) {
        Lotto lotto = new Lotto();

        int count = lotto.purchaseCount(input);

        assertThat(count).isEqualTo(expect);

    }

    @DisplayName("로또 번호 생성")
    @ParameterizedTest
    @CsvSource(value = {"1000:1", "1113455:1113", "12000:12", "12500:12"}, delimiter = ':')
    void makeLottoNumber(int input, int expect) {
        //Lotto lotto = new Lotto();

        //int count = lotto.purchaseCount(input);

        //assertThat(count).isEqualTo(expect);

    }

    @DisplayName("로또 번호는 6개여야 한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", "1", "1, 2 ,4, 5", "2,3,4,5,6,7,8,9" })
    void lottoNumberCountIsSix(String input) {
        Lotto lotto = new Lotto();

        assertThatThrownBy(() -> {
            lotto.makeWinningNumber(input);
        }).isInstanceOf(RuntimeException.class)
                .hasMessage("당첨 번호는 6개여야 합니다.");
    }

    @DisplayName("0 ~ 45 사이 값을 입력 해주세요.")
    @ParameterizedTest
    @ValueSource(strings = {"1, 2, 3, 4, 5, 100", "1, 2, 100, 4, 5, 6"})
    void lottoNumberRange0To45(String input) {
        Lotto lotto = new Lotto();

        assertThatThrownBy(() -> {
            lotto.makeWinningNumber(input);
        }).isInstanceOf(RuntimeException.class)
                .hasMessage("0 ~ 45 사이 값을 입력 해주세요.");
    }

    @DisplayName("숫자가 아닌 값을 입력할 수 없습니다.")
    @ParameterizedTest
    @ValueSource(strings = {"1, d2, df3, 4, 5, 100", "1, df2, 100, 4, a5, 6"})
    void lottoNumberFormatException(String input) {
        Lotto lotto = new Lotto();

        assertThatThrownBy(() -> {
            lotto.makeWinningNumber(input);
        }).isInstanceOf(NumberFormatException.class)
                .hasMessage("숫자가 아닌 값을 입력할 수 없습니다.");
    }

    @DisplayName("당첨 개수 확인")
    @Test
    void lottoNumberEqualCount() {
        Lotto lotto = new Lotto();

        List<Integer> lottoNumbers = Arrays.asList(8, 21, 23, 41, 42, 43);
        List<Integer> winningNumbers = Arrays.asList(8, 21, 23, 41, 42, 45);

        int expect = 5;

        int count = lotto.equalCount(lottoNumbers, winningNumbers);

        assertThat(count).isEqualTo(expect);
    }

    @DisplayName("로또 정답 비교")
    @Test
    void isEqual() {
        Lotto lotto = new Lotto();

        int num = 21;
        List<Integer> winningNumbers = Arrays.asList(8, 21, 23, 41, 42, 45);

        int expect = 1;

        int count = lotto.isEqual(num, winningNumbers);

        assertThat(count).isEqualTo(expect);
    }

    @DisplayName("모든 로또 번호 당첨 개수")
    @Test
    void lottoEqualList() {
        Lotto lotto = new Lotto();

        List<Integer> winningNumbers = Arrays.asList(8, 21, 23, 41, 42, 45);
        List<List<Integer>> lottoNumberList = new ArrayList<>() {{
            add(Arrays.asList(8, 21, 23, 41, 42, 43));
            add(Arrays.asList(3, 5, 11, 16, 32, 38)); }};

            List<Integer> count = lotto.lottoEqualList(lottoNumberList, winningNumbers);

            List<Integer> expect = Arrays.asList(1,0,0,0,0,1,0);

            assertThat(count).isEqualTo(expect);
    }

    @DisplayName("수익 비율")
    @Test
    void rateIsZero() {
        Lotto lotto = new Lotto();

        int lottoCount = 14;
        List<Integer> equalList = Arrays.asList(0,0,0,0,0,0,0);

        double rate = lotto.rate(lottoCount, equalList);

        double expect = 0;

        assertThat(rate).isEqualTo(expect);
    }

    @DisplayName("수익 비율")
    @Test
    void rate() {
        Lotto lotto = new Lotto();

        int lottoCount = 14;
        List<Integer> equalList = Arrays.asList(0,0,0,1,0,0,0);

        double rate = lotto.rate(lottoCount, equalList);

        double expect = 0.35;

        assertThat(rate).isEqualTo(expect);
    }

}