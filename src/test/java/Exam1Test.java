import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class Exam1Test {

    @DisplayName("쉼표(,) 또는 콜론(:) 구분자를 기준으로 분리한 각 숫자의 합을 반환한다")
    @ParameterizedTest
    @CsvSource(value = {"1,2=3", "1,2,3=6","1,2:3=6"}, delimiter = '=')
    void splitAndSumTest(String input, int expected){
        //given
        //String str = "1,23";

        //when
        Exam1 exam1 = new Exam1();
        int sum = exam1.splitAndSum(input);

        //then
        assertThat(sum).isEqualTo(expected);
    }

    @DisplayName("앞의 기본 구분자(쉼표, 콜론)외에 커스텀 구분자를 지정할 수 있다" +
            " 커스텀 구분자는 문자열 앞부분의 “//”와 “\\n” 사이에 위치하는 문자를 커스텀 구분자로 사용한다")
    @ParameterizedTest
    @CsvSource(value = {"//;\\n1;2;3=6", "1.2.4//.\\n.6=13", "1:2+4//+\\n,6=13", "1-2+4//+\\n,6//-\\n-7=20"}, delimiter = '=')
    void customDelimiterTest(String input, int expected){
        Exam1 exam1 = new Exam1();
        int sum = exam1.splitAndSum(input);

        assertThat(sum).isEqualTo(expected);
    }

    @DisplayName("음수 값 전달 예외 처리") //1-2+4//+\n,6//-\n-7 은 -는 구분값? 음수값?.. (일단 구분값으로..)
    @ParameterizedTest
    @ValueSource(strings = {"1,-2", "//;\\n1;2;-3"})
    void NegativeExceptionTest(String input){
        Exam1 exam1 = new Exam1();

        assertThatThrownBy(() -> {
            exam1.splitAndSum(input); // splitAndSum를 선언해야할까요 checkException를 선언해야할까요
        })
                .isInstanceOf(RuntimeException.class)
                .hasMessage("음수 값 전달 에러");

    }

    @DisplayName("숫자가 아닌 요소 전달 예외 처리")
    @ParameterizedTest
    @ValueSource(strings = {"1,n", "//;\\n1;2;*"})
    void NonNumericExceptionTest(String input){
        Exam1 exam1 = new Exam1();

        assertThatThrownBy(() -> {
            exam1.splitAndSum(input);
        })
                .isInstanceOf(RuntimeException.class)
                .hasMessage("숫자가 아닌 요소 전달 에러");

    }

}