import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Exam1 {

    public int splitAndSum(String str){
        //커스텀 구분자 추출
        //커스텀 구분자 추출 부분도 따로 메소드를 만드는게 좋을까요 ?!
        Pattern pattern = Pattern.compile("//(.*?)\\\\n");
        Matcher matcher = pattern.matcher(str);
        String customDelimiter = ":,";

        while (matcher.find()) {
            customDelimiter += matcher.group(1);
            str = str.replace(matcher.group(),"");
        }

        String[] arr = str.split("["+customDelimiter+"]");

        checkException(arr);

        return Arrays.stream(arr).mapToInt(Integer::parseInt).sum();
    }

    //예외 처리 메소드
    public void checkException(String[] arr){
        boolean hasNonNumeric = Arrays.stream(arr)
                .anyMatch(s -> !s.matches("-?\\d+"));

        if (hasNonNumeric) {
            throw new RuntimeException("숫자가 아닌 요소 전달 에러");
        }

        boolean hasNegative = Arrays.stream(arr).mapToInt(Integer::parseInt).anyMatch(n -> n < 0); //Arrays.stream(arr) 중복 제거 필요 ?? 아니면 stream 말고 try catch가 좋은 코드 일까요 ??

        if (hasNegative) {
            throw new RuntimeException("음수 값 전달 에러");
        }

    }

}
