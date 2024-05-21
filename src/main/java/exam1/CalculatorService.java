package exam1;

public class CalculatorService {

    private final Calculator calculator;
    private final CalculatorValidate calculatorValidate;

    public CalculatorService(Calculator calculator, CalculatorValidate calculatorValidate) {
        this.calculator = calculator;
        this.calculatorValidate = calculatorValidate;
    }

    //문자열 나누고 붙이는 메소드
    public int splitAndSum(String str) {
        String[] strArr = calculator.strSplit(str);

        calculatorValidate.checkException(strArr);

        int sum = calculator.strSum(strArr);

        return sum;
    }
}


