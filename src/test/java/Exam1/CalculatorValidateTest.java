package Exam1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CalculatorValidateTest {
    @DisplayName("숫자가 아닌 요소를 전달하면 예외가 발생한다")
    @Test
    void NonNumericExceptionTest() {
        String[] input = {"1", "-2nnn", "0"};

        CalculatorValidate calculatorValidate = new CalculatorValidate();

        assertThatThrownBy(() -> {
            calculatorValidate.checkException(input);
        })
                .isInstanceOf(RuntimeException.class)
                .hasMessage("숫자가 아닌 요소는 전달이 불가능 합니다");

    }

    @DisplayName("음수 값을 전달하면 예외가 발생한다")
    @Test
    void NegativeExceptionTest() {
        String[] input = {"1", "-2", "0"};

        CalculatorValidate calculatorValidate = new CalculatorValidate();

        assertThatThrownBy(() -> {
            calculatorValidate.checkException(input);
        })
                .isInstanceOf(RuntimeException.class)
                .hasMessage("음수 값은 전달이 불가능 합니다");
    }
}
