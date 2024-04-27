package Exam1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class CalculatorServiceTest {
    @DisplayName("쉼표(,) 또는 콜론(:) 구분자를 기준으로 분리한 각 숫자의 합을 반환한다")
    @ParameterizedTest
    @CsvSource(value = {"1,2=3", "1,2,3=6", "1,2:3=6"}, delimiter = '=')
    void splitAndSumTest(String input, int expected) {
        CalculatorService calculatorService = new CalculatorService(new Calculator());
        int sum = calculatorService.splitAndSum(input);

        assertThat(sum).isEqualTo(expected);
    }

    @DisplayName("앞의 기본 구분자(쉼표, 콜론)외에 커스텀 구분자를 지정할 수 있다" +
            "커스텀 구분자는 문자열 앞부분의 “//”와 “\\n” 사이에 위치하는 문자를 커스텀 구분자로 사용한다")
    @ParameterizedTest
    @CsvSource(value = {"//;\\n1;2;3=6", "1.2.4//.\\n.6=13", "1:2+4//+\\n,6=13", "1-2+4//+\\n,6//-\\n-7=20"}, delimiter = '=')
    void customDelimiterTest(String input, int expected) {
        CalculatorService calculatorService = new CalculatorService(new Calculator());
        int sum = calculatorService.splitAndSum(input);

        assertThat(sum).isEqualTo(expected);
    }
}
