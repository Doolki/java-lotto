package exam2;

import exam2.lotto.PurchaseLottoList;
import exam2.lotto.WinningLottoNumber;
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
            new PurchaseLottoList(input);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("로또 구입 가격은 1000원 이상 이여야 합니다");

    }

    @DisplayName("로또를 구입한 개수를 계산합니다")
    @ParameterizedTest
    @CsvSource(value = {"1000:1", "1113455:1113", "12000:12", "12500:12"}, delimiter = ':')
    void purchaseCount(int input, int expect) {
        PurchaseLottoList purchaseLottoList = new PurchaseLottoList(input);

        assertThat(purchaseLottoList.getCount()).isEqualTo(expect);
    }

    @DisplayName("랜덤으로 생성된 로또 번호는 1~45 사이 값 입니다.")
    @Test
    void checkLottoNumberRange() {
        PurchaseLottoList purchaseLottoList = new PurchaseLottoList(1000);

        List<Integer> numbers = purchaseLottoList.getNumbers(0);

        assertThat(numbers)
            .allSatisfy(number -> assertThat(number).isBetween(1, 45));
    }

    @DisplayName("랜덤으로 생성된 로또 번호는 6개로 이루어져 있습니다")
    @Test
    void checkLottoNumberSize() {
        PurchaseLottoList purchaseLottoList = new PurchaseLottoList(1000);

        assertThat(purchaseLottoList.getNumberList().get(0)).hasSize(6);
    }

    @DisplayName("로또 당첨 번호는 6개여야 합니다")
    @ParameterizedTest
    @ValueSource(strings = {"", "1", "1, 2 ,4, 5", "2,3,4,5,6,7,8,9"})
    void lottoNumberCountIsSix(String input) {

        assertThatThrownBy(() -> {
            new WinningLottoNumber(input);
        }).isInstanceOf(RuntimeException.class)
            .hasMessage("당첨 번호는 6개여야 합니다");
    }

    @DisplayName("로또 당첨 번호는 1 ~ 45 사이 값이여야 합니다")
    @ParameterizedTest
    @ValueSource(strings = {"1, 2, 3, 4, 5, 100", "1, 2, 100, 4, 5, 6"})
    void lottoNumberRange0To45(String input) {

        assertThatThrownBy(() -> {
            new WinningLottoNumber(input);
        }).isInstanceOf(RuntimeException.class)
            .hasMessage("1 ~ 45 사이 값을 입력 해주세요");
    }

    @DisplayName("로또 당첨 번호는 숫자가 아닌 값을 입력할 수 없습니다")
    @ParameterizedTest
    @ValueSource(strings = {"1, d2, df3, 4, 5, 100", "1, df2, 100, 4, a5, 6"})
    void lottoNumberFormatException(String input) {
        assertThatThrownBy(() -> {
            new WinningLottoNumber(input);
        }).isInstanceOf(NumberFormatException.class)
            .hasMessage("숫자가 아닌 값을 입력할 수 없습니다");
    }

    @DisplayName("로또 당첨 번호는 중복될 수 없습니다")
    @ParameterizedTest
    @ValueSource(strings = {"1, 2, 3, 4, 1, 45"})
    void lottoDuplicateException(String input) {
        assertThatThrownBy(() -> {
            new WinningLottoNumber(input);
        }).isInstanceOf(RuntimeException.class)
            .hasMessage("중복된 값은 입력할 수 없습니다");
    }

    @DisplayName("로또 당첨 개수를 확인합니다")
    @Test
    void lottoNumberEqualCount() {
        WinningLottoNumber winningNumbers = new WinningLottoNumber("1,2,3,4,5,6");
        PurchaseLottoList purchaseLottoList = new PurchaseLottoList() {{
            add(Arrays.asList(8, 21, 23, 41, 42, 43));
            add(Arrays.asList(3, 5, 11, 16, 32, 38));
        }};

        int expect = 0;

        int count = purchaseLottoList.equalCount(purchaseLottoList.getNumbers(0), winningNumbers);

        assertThat(count).isEqualTo(expect);
    }

    @DisplayName("로또 번호가 정답인지 비교합니다")
    @Test
    void isEqual() {
        WinningLottoNumber winningNumbers = new WinningLottoNumber("1,2,3,4,5,6");
        PurchaseLottoList purchaseLottoList = new PurchaseLottoList() {{
            add(Arrays.asList(8, 21, 23, 41, 42, 43));
            add(Arrays.asList(3, 5, 11, 16, 32, 38));
        }};
        int expect = 0;

        int count = purchaseLottoList.isEqual(purchaseLottoList.getNumberList().get(0).get(0),
            winningNumbers);

        assertThat(count).isEqualTo(expect);
    }

    @DisplayName("모든 로또 번호 당첨 개수를 계산합니다")
    @Test
    void lottoEqualList() {
        WinningLottoNumber winningNumbers = new WinningLottoNumber("1,2,3,4,5,6");
        PurchaseLottoList purchaseLottoList = new PurchaseLottoList() {{
            add(Arrays.asList(8, 21, 23, 41, 42, 43));
            add(Arrays.asList(3, 5, 11, 16, 32, 38));
        }};

        purchaseLottoList.lottoEqualList(winningNumbers);

        List<Integer> expect = Arrays.asList(1, 0, 1, 0, 0, 0, 0);

        assertThat(purchaseLottoList.getEqualList()).isEqualTo(expect);
    }

    @DisplayName("수익 비율를 계산합니다")
    @Test
    void rate() {
        WinningLottoNumber winningNumbers = new WinningLottoNumber("1,2,3,4,5,6");
        PurchaseLottoList purchaseLottoList = new PurchaseLottoList() {{
            add(Arrays.asList(8, 21, 23, 41, 42, 43));
            add(Arrays.asList(3, 5, 11, 16, 32, 38));
        }};

        purchaseLottoList.lottoEqualList(winningNumbers);

        double expect = 0;

        assertThat(purchaseLottoList.getRate()).isEqualTo(expect);
    }

}
