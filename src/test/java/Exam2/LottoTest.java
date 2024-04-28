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

    @DisplayName("로또 구입 가격은 1000원 이상 이여야 합니다")
    @ParameterizedTest
    @ValueSource(ints = {999})
    void purchasePriceException(int input) {
        Lotto lotto = new Lotto();

        assertThatThrownBy(() -> {
            lotto.purchaseCount(input);
        }).isInstanceOf(RuntimeException.class)
            .hasMessage("로또 구입 가격은 1000원 이상 이여야 합니다");

    }

    @DisplayName("로또를 구입한 개수를 계산합니다")
    @ParameterizedTest
    @CsvSource(value = {"1000:1", "1113455:1113", "12000:12", "12500:12"}, delimiter = ':')
    void purchaseCount(int input, int expect) {
        Lotto lotto = new Lotto();

        int count = lotto.purchaseCount(input);

        assertThat(count).isEqualTo(expect);
    }

    @DisplayName("랜덤으로 생성된 로또 번호는 1~45 사이 값 입니다.")
    @Test
    void checkLottoNumberRange() {
        Lotto lotto = new Lotto();

        List<Integer> lottoNumber = lotto.makeLottoNumber();

        for (Integer num : lottoNumber) {
            assertThat(num).isBetween(1, 45);
        }
    }

    @DisplayName("랜덤으로 생성된 로또 번호는 6개로 이루어져 있습니다")
    @Test
    void checkLottoNumberSize() {
        Lotto lotto = new Lotto();

        List<Integer> lottoNumber = lotto.makeLottoNumber();

        assertThat(lottoNumber).hasSize(6);
    }

    @DisplayName("로또 당첨 번호는 6개여야 합니다")
    @ParameterizedTest
    @ValueSource(strings = {"", "1", "1, 2 ,4, 5", "2,3,4,5,6,7,8,9"})
    void lottoNumberCountIsSix(String input) {
        Lotto lotto = new Lotto();

        assertThatThrownBy(() -> {
            lotto.makeWinningNumber(input);
        }).isInstanceOf(RuntimeException.class)
            .hasMessage("당첨 번호는 6개여야 합니다");
    }

    @DisplayName("로또 당첨 번호는 1 ~ 45 사이 값이여야 합니다")
    @ParameterizedTest
    @ValueSource(strings = {"1, 2, 3, 4, 5, 100", "1, 2, 100, 4, 5, 6"})
    void lottoNumberRange0To45(String input) {
        Lotto lotto = new Lotto();

        assertThatThrownBy(() -> {
            lotto.makeWinningNumber(input);
        }).isInstanceOf(RuntimeException.class)
            .hasMessage("1 ~ 45 사이 값을 입력 해주세요");
    }

    @DisplayName("로또 당첨 번호는 숫자가 아닌 값을 입력할 수 없습니다")
    @ParameterizedTest
    @ValueSource(strings = {"1, d2, df3, 4, 5, 100", "1, df2, 100, 4, a5, 6"})
    void lottoNumberFormatException(String input) {
        Lotto lotto = new Lotto();

        assertThatThrownBy(() -> {
            lotto.makeWinningNumber(input);
        }).isInstanceOf(NumberFormatException.class)
            .hasMessage("숫자가 아닌 값을 입력할 수 없습니다");
    }

    @DisplayName("로또 당첨 번호는 중복될 수 없습니다")
    @ParameterizedTest
    @ValueSource(strings = {"1, 2, 3, 4, 1, 45"})
    void lottoDuplicateException(String input) {
        Lotto lotto = new Lotto();

        assertThatThrownBy(() -> {
            lotto.makeWinningNumber(input);
        }).isInstanceOf(RuntimeException.class)
            .hasMessage("중복된 값은 입력할 수 없습니다");
    }

    @DisplayName("로또 당첨 개수를 확인합니다")
    @Test
    void lottoNumberEqualCount() {
        Lotto lotto = new Lotto();

        List<Integer> lottoNumbers = Arrays.asList(8, 21, 23, 41, 42, 43);
        List<Integer> winningNumbers = Arrays.asList(8, 21, 23, 41, 42, 45);

        int expect = 5;

        int count = lotto.equalCount(lottoNumbers, winningNumbers);

        assertThat(count).isEqualTo(expect);
    }

    @DisplayName("로또 번호가 정답인지 비교합니다")
    @Test
    void isEqual() {
        Lotto lotto = new Lotto();

        int lottoNum = 21;
        List<Integer> winningNumbers = Arrays.asList(8, 21, 23, 41, 42, 45);

        int expect = 1;

        int count = lotto.isEqual(lottoNum, winningNumbers);

        assertThat(count).isEqualTo(expect);
    }

    @DisplayName("모든 로또 번호 당첨 개수를 계산합니다")
    @Test
    void lottoEqualList() {
        Lotto lotto = new Lotto();

        List<Integer> winningNumbers = Arrays.asList(8, 21, 23, 41, 42, 45);
        List<List<Integer>> lottoNumberList = new ArrayList<>() {{
            add(Arrays.asList(8, 21, 23, 41, 42, 43));
            add(Arrays.asList(3, 5, 11, 16, 32, 38));
        }};

        List<Integer> count = lotto.lottoEqualList(lottoNumberList, winningNumbers);

        List<Integer> expect = Arrays.asList(1, 0, 0, 0, 0, 1, 0);

        assertThat(count).isEqualTo(expect);
    }

    @DisplayName("수익 비율를 계산합니다")
    @ParameterizedTest
    @CsvSource({
        "14, 0, 0, 0, 0, 0, 0, 0",
        "14, 0, 0, 0, 1, 0, 0, 0.35"
    })
    void rate(int lottoCount, int equal0, int equal1, int equal2, int equal3, int equal4,
        int equal5, double expect) {
        Lotto lotto = new Lotto();

        List<Integer> equalList = Arrays.asList(equal0, equal1, equal2, equal3, equal4, equal5);

        double rate = lotto.rate(lottoCount, equalList);

        assertThat(rate).isEqualTo(expect);
    }

}