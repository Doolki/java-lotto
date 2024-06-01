package exam2.lotto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningNumberTest {

    @DisplayName("당첨 번호와 보너스 번호는 중복될 수 없습니다.")
    @Test
    void 당첨_번호와_보너스_번호는_중복될_수_없습니다() {
        assertThatThrownBy(() -> {
            WinningNumber winningNumbers = new WinningNumber("1,2,3,4,5,40", 40);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("당첨 번호와 보너스 번호는 중복될 수 없습니다.");
    }

    @DisplayName("보너스 번호는 1 ~ 45 사이 값이여야 합니다")
    @Test
    void 보너스_번호는_1_45_사이_값이여야_합니다() {
        assertThatThrownBy(() -> {
            new WinningNumber("1,2,3,4,5,40", 46);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("로또번호는 1 ~ 45 사이 값 이어야 합니다");
    }

}
