package Exam1;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    private static final String STR_SPLIT_PATTERN = "//(.*?)\\\\n";
    private static final Pattern pattern = Pattern.compile(STR_SPLIT_PATTERN);

    //문자열 나누기
    public String[] strSplit(String str) {
        if (str == null) {
            throw new NullPointerException("문자는 빈값이 될 수 없습니다");
        }

        Matcher matcher = pattern.matcher(str);
        String customDelimiter = ":,";

        while (matcher.find()) {
            customDelimiter += matcher.group(1);
            str = str.replace(matcher.group(), "");
        }

        String[] arr = str.split("[" + customDelimiter + "]");

        return arr;
    }

    //합 구하기
    public int strSum(String[] arr) {
        return Arrays.stream(arr).mapToInt(Integer::parseInt).sum();
    }
}
