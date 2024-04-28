package Exam2;

import java.util.*;

public class Lotto {

    //로또 구입 개수를 계산합니다
    public int purchaseCount(int price) {
        if (price < 1000) {
            throw new RuntimeException("로또 구입 가격은 1000원 이상 이여야 합니다");
        }

        return (int) (price / LottoDTO.LOTTO_PRICE);
    }

    //로또 번호를 생성합니다
    public List<Integer> makeLottoNumber() {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 45; i++) {
            list.add(i);
        }

        Collections.shuffle(list);
        List<Integer> lottoNumberList = list.subList(0, 6);
        Collections.sort(lottoNumberList);

        return lottoNumberList;
    }

    //로또 구입 개수 만큼 로또 번호를 생성합니다
    public List<List<Integer>> makeLottoNumberList(int count) {
        List<List<Integer>> lottoNumberList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            lottoNumberList.add(makeLottoNumber());
        }

        return lottoNumberList;
    }

    //로또 정답 번호를 생성합니다
    public List<Integer> makeWinningNumber(String winningStr) {
        if (winningStr.isEmpty()) {
            throw new RuntimeException("당첨 번호는 6개여야 합니다");
        }

        String[] splitStr = winningStr.split(",");

        List<Integer> winningNumberList = new ArrayList<>();

        for (String s : splitStr) {
            int num = 0;

            try {
                num = Integer.parseInt(s.trim());
            } catch (NumberFormatException e) {
                throw new NumberFormatException("숫자가 아닌 값을 입력할 수 없습니다");
            }

            if (!(num <= 45 && num > 0)) {
                throw new RuntimeException("1 ~ 45 사이 값을 입력 해주세요");
            }
            //같은 값 예외처리 메소드 만들기
            winningNumberList.add(Integer.parseInt(s.trim()));
        }

        if (winningNumberList.size() != 6) {
            throw new RuntimeException("당첨 번호는 6개여야 합니다");
        }

        Set<Integer> set = new HashSet<>(winningNumberList);
        if (set.size() != winningNumberList.size()) {
            throw new RuntimeException("중복된 값은 입력할 수 없습니다");
        }

        return winningNumberList;
    }

    //모든 로또 번호의 당첨 개수를 계산합니다
    public List<Integer> lottoEqualList(List<List<Integer>> lottoNumberList,
        List<Integer> winningNumber) {
        List<Integer> equalList = Arrays.asList(0, 0, 0, 0, 0, 0, 0);

        for (List<Integer> list : lottoNumberList) {
            int index = equalCount(list, winningNumber);
            equalList.set(index, equalList.get(index) + 1);
        }

        return equalList;
    }

    //로또 당첨 개수를 계산합니다
    public int equalCount(List<Integer> lottoNumberList, List<Integer> winningNumberList) {
        int equalCount = 0;
        for (Integer lottoNum : lottoNumberList) {
            equalCount += isEqual(lottoNum, winningNumberList);
        }
        return equalCount;
    }

    //로또 번호 1개 당첨 여부를 확인합니다
    public int isEqual(int lottoNum, List<Integer> winningNumberList) {
        int equal = 0;
        if (winningNumberList.contains(lottoNum)) {
            equal = 1;
        }
        return equal;
    }

    //당첨 통계를 계산합니다
    public double rate(int lottoCount, List<Integer> equalList) {
        double sum = 0;
        for (int i = 0; i < equalList.size(); i++) {
            sum += equalList.get(i) * LottoDTO.PRIZE[i];
        }

        if (sum == 0) {
            return 0;
        }

        return Math.floor((sum / (lottoCount * LottoDTO.LOTTO_PRICE)) * 100.0) / 100.0;
    }

    //당첨 통계 메세지
    public String benefit(double rate) {
        String str = "(기준이 1이기 때문에 결과적으로 손해라는 의미임)";

        if (rate >= 1) {
            str = "(기준이 1이기 때문에 결과적으로 이득이라는 의미임)";
        }

        return str;
    }
}
