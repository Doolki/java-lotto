package Exam1;

public class CalculatorValidate {

    public void checkException(String[] arr) {
        for (String s : arr) {
            int num = isNum(s);
            isPositive(num);
        }
    }


    //숫자가 아닌 요소 전달 에러
    public int isNum(String str) {
        try {
            int num = Integer.parseInt(str);
            return num;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("숫자가 아닌 요소는 전달이 불가능 합니다");
        }
    }

    //음수인지 확인
    public void isPositive(int num) {
        if (num < 0) {
            throw new RuntimeException("음수 값은 전달이 불가능 합니다");
        }
    }
}

