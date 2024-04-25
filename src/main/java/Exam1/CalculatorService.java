package Exam1;

public class CalculatorService {

    //문자열 나누고 붙이는 메소드
    public int splitAndSum(String str) {
        Calculator calculator = new Calculator();

        String[] strArr = calculator.strSplit(str);

        Exception exception = new Exception();
        exception.checkException(strArr);

        int sum = calculator.strSum(strArr);

        return sum;
    }
}

