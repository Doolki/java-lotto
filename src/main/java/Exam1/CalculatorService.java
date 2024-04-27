package Exam1;

public class CalculatorService {
    private final Calculator calculator;

    public CalculatorService(Calculator calculator) {
        this.calculator = calculator;
    }

    //문자열 나누고 붙이는 메소드
    public int splitAndSum(String str) {
        Calculator calculator = new Calculator();
        String[] strArr = calculator.strSplit(str);

        CalculatorValidate calculatorValidate = new CalculatorValidate();
        calculatorValidate.checkException(strArr);

        int sum = calculator.strSum(strArr);

        return sum;
    }
}

