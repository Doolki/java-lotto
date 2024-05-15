package exam1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CalculatorTest {

    @DisplayName("쉼표(,) 또는 콜론(:) 구분자를 기준으로 분리한 배열을 반환한다")
    @Test
    void splitTest() {

        String input = "1,2,3";
        String[] expected = {"1", "2", "3"};

        Calculator calculator = new Calculator();
        String[] arr = calculator.strSplit(input);

        assertThat(arr).isEqualTo(expected);
    }

    @DisplayName("입력하는 문자열이 null이면 예외가 발생한다")
    @Test
    void strNullTest() {
        String input = null;

        Calculator calculator = new Calculator();
/*
        assertThatThrownBy(() -> {
            calculator.strSplit(input);
        }).isInstanceOf(NullPointerException.class)
                .hasMessage("문자는 빈값이 될 수 없습니다");*/

        assertThatNullPointerException()
            .isThrownBy(() -> calculator.strSplit(input))
            .withMessage("문자는 빈값이 될 수 없습니다");

    }

    @DisplayName("앞의 기본 구분자(쉼표, 콜론)외에 커스텀 구분자를 지정할 수 있다" +
        "커스텀 구분자는 문자열 앞부분의 “//”와 “\\n” 사이에 위치하는 문자를 커스텀 구분자로 사용한다")
    @Test
    void customDelimiterTest() {
        String input = "//;\\n1;2;3";
        String[] expected = {"1", "2", "3"};

        Calculator calculator = new Calculator();
        String[] strings = calculator.strSplit(input);

        assertThat(strings).isEqualTo(expected);
    }

    @DisplayName("커스텀 문자열은 null을 허용한다")
    @Test
    void customDelimiterNullTest() {
        String input = "//\\n1,23";
        String[] expected = {"1", "23"};

        Calculator calculator = new Calculator();
        String[] strings = calculator.strSplit(input);

        assertThat(strings).isEqualTo(expected);
    }

    @DisplayName("커스텀 구분자는 1개 이상이 될 수 있다")
    @Test
    void manyCustomDelimiterTest() {
        String input = "1=2+4//+\\n,6//=\\n=7";
        String[] expected = {"1", "2", "4", "6", "7"};

        Calculator calculator = new Calculator();
        String[] strings = calculator.strSplit(input);

        assertThat(strings).isEqualTo(expected);
    }

    @DisplayName("문자열의 합을 구한다")
    @Test
    void sumTest() {
        String[] input = {"1", "2", "4"};
        int expected = 7;

        Calculator calculator = new Calculator();
        int sum = calculator.strSum(input);

        assertThat(sum).isEqualTo(expected);
    }

}

