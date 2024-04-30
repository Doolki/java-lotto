package exam2.lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//로또 당첨 번호 클래스
public class WinningLottoNumber {

    private final static int lottoStartNumber = 1;
    private final static int lottoEndNumber = 45;

    private List<Integer> winningNumberList = new ArrayList<>();

    //로또 정답 번호를 생성합니다
    public WinningLottoNumber(String winningStr) {
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

            if (!(num <= lottoEndNumber && num >= lottoStartNumber)) {
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

        this.winningNumberList = winningNumberList;
    }

    public List<Integer> getWinningNumberList() {
        return winningNumberList;
    }

}
