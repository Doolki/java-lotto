package exam2;

import exam2.lotto.LottoNumbers;
import exam2.lotto.Purchase;
import exam2.lotto.Winning;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {

    @DisplayName("로또 구입 가격은 1000원 이상 이여야 합니다")
    @ParameterizedTest
    @ValueSource(ints = {999})
    void purchasePriceException(int input) {

        assertThatThrownBy(() -> {
            new Purchase(input);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("로또 구입 가격은 1000원 이상 이여야 합니다");

    }

    @DisplayName("로또를 구입한 개수를 계산합니다")
    @ParameterizedTest
    @CsvSource(value = {"1000:1", "1113455:1113", "12000:12", "12500:12"}, delimiter = ':')
    void purchaseCount(int input, int expect) {
        Purchase purchase = new Purchase(input);

        assertThat(purchase.getCount()).isEqualTo(expect);
    }

    @DisplayName("랜덤으로 생성된 로또 번호는 1~45 사이 값 입니다.")
    @Test
    void checkLottoNumberRange() {
        LottoNumbers lottoNumbers = new LottoNumbers(1);

        List<Integer> numbers = lottoNumbers.getNumberList().get(0);

        assertThat(numbers)
            .allSatisfy(number -> assertThat(number).isBetween(1, 45));
    }

    @DisplayName("랜덤으로 생성된 로또 번호는 6개로 이루어져 있습니다")
    @Test
    void checkLottoNumberSize() {
        LottoNumbers lottoNumbers = new LottoNumbers(1);

        List<Integer> numbers = lottoNumbers.getNumberList().get(0);

        assertThat(numbers).hasSize(6);
    }

    @DisplayName("로또 당첨 번호는 6개여야 합니다")
    @ParameterizedTest
    @ValueSource(strings = {"", "1", "1, 2 ,4, 5", "2,3,4,5,6,7,8,9"})
    void lottoNumberCountIsSix(String input) {

        assertThatThrownBy(() -> {
            new Winning(input);
        }).isInstanceOf(RuntimeException.class)
            .hasMessage("로또 번호는 6개여야 합니다");
    }

    @DisplayName("로또 당첨 번호는 1 ~ 45 사이 값이여야 합니다")
    @ParameterizedTest
    @ValueSource(strings = {"1, 2, 3, 4, 5, 100", "1, 2, 100, 4, 5, 6"})
    void lottoNumberRange0To45(String input) {

        assertThatThrownBy(() -> {
            new Winning(input);
        }).isInstanceOf(RuntimeException.class)
            .hasMessage("1 ~ 45 사이 값을 입력 해주세요");
    }

    @DisplayName("로또 당첨 번호는 숫자가 아닌 값을 입력할 수 없습니다")
    @ParameterizedTest
    @ValueSource(strings = {"1, d2, df3, 4, 5, 100", "1, df2, 100, 4, a5, 6"})
    void lottoNumberFormatException(String input) {
        assertThatThrownBy(() -> {
            new Winning(input);
        }).isInstanceOf(NumberFormatException.class)
            .hasMessage("숫자가 아닌 값을 입력할 수 없습니다");
    }

    @DisplayName("로또 당첨 번호는 중복될 수 없습니다")
    @ParameterizedTest
    @ValueSource(strings = {"1, 2, 3, 4, 1, 45"})
    void lottoDuplicateException(String input) {
        assertThatThrownBy(() -> {
            new Winning(input);
        }).isInstanceOf(RuntimeException.class)
            .hasMessage("중복된 값은 입력할 수 없습니다");
    }

    @DisplayName("로또 당첨 개수를 확인합니다")
    @Test
    void lottoNumberEqualCount() {
        Winning winningNumbers = new Winning("1,2,3,4,5,6");
        Purchase purchase = new Purchase() {{
            add(Arrays.asList(8, 21, 23, 41, 42, 43));
            add(Arrays.asList(3, 5, 11, 16, 32, 38));
        }};

        int expect = 0;

        int count = purchase.equalCount(purchase.getLottoNumbers().getNumberList().get(0),
            winningNumbers);

        assertThat(count).isEqualTo(expect);
    }

    @DisplayName("로또 번호가 정답인지 비교합니다")
    @Test
    void isEqual() {
        Winning winningNumbers = new Winning("1,2,3,4,5,6");
        Purchase purchase = new Purchase() {{
            add(Arrays.asList(8, 21, 23, 41, 42, 43));
            add(Arrays.asList(3, 5, 11, 16, 32, 38));
        }};
        int expect = 0;

        int count = purchase.isEqual(purchase.getLottoNumbers().getNumberList().get(0).get(0),
            winningNumbers);

        assertThat(count).isEqualTo(expect);
    }

    @DisplayName("모든 로또 번호 당첨 개수를 계산합니다")
    @Test
    void lottoEqualList() {
        Winning winningNumbers = new Winning("1,2,3,4,5,6");
        Purchase purchase = new Purchase() {{
            add(Arrays.asList(8, 21, 23, 41, 42, 43));
            add(Arrays.asList(3, 5, 11, 16, 32, 38));
        }};

        purchase.lottoEqualList(winningNumbers);

        List<Integer> expect = Arrays.asList(1, 0, 1, 0, 0, 0, 0);

        assertThat(purchase.getEqualList()).isEqualTo(expect);
    }

    @DisplayName("수익 비율을 계산합니다")
    @Test
    void rate() {
        Winning winningNumbers = new Winning("1,2,3,4,5,6");
        Purchase purchase = new Purchase() {{
            add(Arrays.asList(8, 21, 23, 41, 42, 43));
            add(Arrays.asList(3, 5, 11, 16, 32, 38));
            add(Arrays.asList(7, 11, 16, 35, 36, 44));
            add(Arrays.asList(1, 8, 11, 31, 41, 42));
            add(Arrays.asList(13, 14, 16, 38, 42, 45));
            add(Arrays.asList(7, 11, 30, 40, 42, 43));
            add(Arrays.asList(2, 13, 22, 32, 38, 45));
            add(Arrays.asList(23, 25, 33, 36, 39, 41));
            add(Arrays.asList(1, 3, 5, 14, 22, 45));
            add(Arrays.asList(5, 9, 38, 41, 43, 44));
            add(Arrays.asList(2, 8, 9, 18, 19, 21));
            add(Arrays.asList(13, 14, 18, 21, 23, 35));
            add(Arrays.asList(17, 21, 29, 37, 42, 45));
            add(Arrays.asList(3, 8, 27, 30, 35, 44));
        }};

        purchase.lottoEqualList(winningNumbers);

        double expect = 0.35;

        assertThat(purchase.getRate()).isEqualTo(expect);
    }
}
