package Exam1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class ExceptionTest {
    @DisplayName("숫자가 아닌 요소 전달 예외 처리")
    @Test
    void NonNumericExceptionTest() {
        String[] input = {"1","-2nnn","0"};

        Exception exception = new Exception();

        assertThatThrownBy(() -> {
            exception.checkException(input);
        })
                .isInstanceOf(RuntimeException.class)
                .hasMessage("숫자가 아닌 요소 전달 에러");

    }

    @DisplayName("음수 값 전달 예외 처리")
    @Test
    void NegativeExceptionTest() {
        String[] input = {"1","-2","0"};

        Exception exception = new Exception();

        assertThatThrownBy(() -> {
            exception.checkException(input);
        })
                .isInstanceOf(RuntimeException.class)
                .hasMessage("음수 값 전달 에러");
    }
}
