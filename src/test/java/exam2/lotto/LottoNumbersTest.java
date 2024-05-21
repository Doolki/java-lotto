package exam2.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("로또 계산기")
class LottoNumbersTest {

    @DisplayName("로또 구입 가격은 1000원 이상 이여야 합니다")
    @ParameterizedTest
    @ValueSource(ints = {999})
    void 로또_구입_가격은_1000원_이상이여야_합니다(int input) {

        assertThatThrownBy(() -> {
            PurchaseTicket.createAutoLottoNumber(input);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("로또 구입 가격은 1000원 이상 이여야 합니다");

    }

    @DisplayName("로또를 구입한 개수를 계산합니다")
    @ParameterizedTest
    @CsvSource(value = {"1000:1", "1113455:1113", "12000:12", "12500:12"}, delimiter = ':')
    void 로또를_구입한_개수를_계산합니다(int input, int expect) {
        PurchaseTicket purchase = PurchaseTicket.createAutoLottoNumber(input);

        assertThat(purchase.getCount()).isEqualTo(expect);
    }

    @DisplayName("랜덤으로 생성된 로또 번호는 1~45 사이 값 입니다")
    @Test
    void 랜덤으로_생성된_로또번호는_1_45_사이_값_입니다() {
        LottoNumbers lottoNumbers = new LottoNumbers();

        assertThat(lottoNumbers.getNumberList()).allMatch(number -> number >= 1 && number <= 45);
    }

    @DisplayName("랜덤으로 생성된 로또 번호는 6개로 이루어져 있습니다")
    @Test
    void 랜덤으로_생성된_로또_번호는_6개로_이루어져_있습니다() {
        LottoNumbers lottoNumbers = new LottoNumbers();

        assertThat(lottoNumbers.getNumberList()).hasSize(6);
    }

    @DisplayName("로또 당첨 번호는 6개여야 합니다")
    @ParameterizedTest
    @ValueSource(strings = {"", "1", "1, 2 ,4, 5", "2,3,4,5,6,7,8,9"})
    void 로또_당첨_번호는_6개여야_합니다(String input) {

        assertThatThrownBy(() -> {
            new WinningNumber(input);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("로또 번호는 6개여야 합니다");
    }

    @DisplayName("로또 당첨 번호는 1 ~ 45 사이 값이여야 합니다")
    @ParameterizedTest
    @ValueSource(strings = {"1, 2, 3, 4, 5, 100", "1, 2, 100, 4, 5, 6"})
    void 로또_당첨_번호는_1_45_사이_값이여야_합니다(String input) {

        assertThatThrownBy(() -> {
            new WinningNumber(input);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("1 ~ 45 사이 값을 입력 해주세요");
    }

    @DisplayName("로또 당첨 번호는 숫자가 아닌 값을 입력할 수 없습니다")
    @ParameterizedTest
    @ValueSource(strings = {"1, d2, df3, 4, 5, 100", "1, df2, 100, 4, a5, 6"})
    void 로또_당첨_번호는_숫자가_아닌_값을_입력할_수_없습니다(String input) {
        assertThatThrownBy(() -> {
            new WinningNumber(input);
        }).isInstanceOf(NumberFormatException.class)
            .hasMessage("숫자가 아닌 값을 입력할 수 없습니다");
    }

    @DisplayName("로또 당첨 번호는 중복될 수 없습니다")
    @ParameterizedTest
    @ValueSource(strings = {"1, 2, 3, 4, 1, 45"})
    void 로또_당첨_번호는_중복될_수_없습니다(String input) {
        assertThatThrownBy(() -> {
            new WinningNumber(input);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("중복된 값은 입력할 수 없습니다");
    }


}