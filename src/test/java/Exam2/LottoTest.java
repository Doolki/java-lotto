package Exam2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

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


}