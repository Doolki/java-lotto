package Exam2;

import java.util.*;

public class Lotto {

    private static final int LOTTO_PRICE = 1000;

    private final static int[] EQUALS = {0, 0, 0, 5000, 50000, 1500000, 2000000000};

    public int purchaseCount(int price) {

        if (price == 0) {
            throw new RuntimeException("로또 구입 가격은 0이 될 수 없습니다.");
        }

        return (int) (price/LOTTO_PRICE);
    }

    public List<Integer> makeLottoNumber() {
        List<Integer> list = new ArrayList<>();
        for (int i=1;i<=45;i++) {
            list.add(i);
        }

        Collections.shuffle(list);
        List<Integer> lottoNumber = list.subList(0, 6);
        Collections.sort(lottoNumber);

        return lottoNumber;
    }

    public List<List<Integer>> makeLottoNumberList(int count) {
        List<List<Integer>> lottoNumberList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            lottoNumberList.add(makeLottoNumber());
        }

        return lottoNumberList;
    }
    public List<Integer> makeWinningNumber(String winningStr) {

        if (winningStr.isEmpty()) {
            throw new RuntimeException("당첨 번호는 6개여야 합니다.");
        }

        String[] splitStr = winningStr.split(",");
        List<Integer> winningNumber = new ArrayList<>();
        for (String s : splitStr) {
            int num = 0;

            try {
                num = Integer.parseInt(s.trim());
            } catch (NumberFormatException e) {
                throw new NumberFormatException("숫자가 아닌 값을 입력할 수 없습니다.");
            }

            if (!(num <= 45 && num > 0)) {
                throw new RuntimeException("0 ~ 45 사이 값을 입력 해주세요.");
            }
            //같은 값 예외처리 메소드 만들기
            winningNumber.add(Integer.parseInt(s.trim()));
        }

        if (winningNumber.size() != 6) {
            throw new RuntimeException("당첨 번호는 6개여야 합니다.");
        }

        return winningNumber;
    }

    public int equalCount(List<Integer> lottoNumber, List<Integer> winningNumber) {
        int equalCount = 0;
        for (Integer num : lottoNumber) {
            equalCount += isEqual(num, winningNumber);
        }
        return equalCount;
    }

    public int isEqual(int num, List<Integer> winningNumber) {
        int equal = 0;
        if (winningNumber.contains(num)) {
            equal = 1;
        }
        return equal;
    }

    public List<Integer> lottoEqualList(List<List<Integer>> lottoNumberList, List<Integer> winningNumber) {
        List<Integer> equalList = Arrays.asList(0,0,0,0,0,0,0);

        for (List<Integer> list : lottoNumberList) {
            int index = equalCount(list, winningNumber);
            equalList.set(index, equalList.get(index) + 1);
        }

        return equalList;
    }

    public double rate(int lottoCount, List<Integer> equalList) {
        double sum = 0;
        for (int i = 0; i < equalList.size(); i++) {
            sum += equalList.get(i) * EQUALS[i];
        }

        if (sum == 0) {
            return 0;
        }

        return Math.floor((sum / (lottoCount * LOTTO_PRICE)) * 100.0) / 100.0;
    }

    public String benefit(double rate) {
        String str = "(기준이 1이기 때문에 결과적으로 손해라는 의미임)";

        if (rate >= 1) {
            str = "(기준이 1이기 때문에 결과적으로 이득이라는 의미임)";
        }

        return str;
    }
}
