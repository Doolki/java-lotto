package exam2.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoNumbers {

    /**
     * 로또 시작 번호
     */
    public static final int LOTTO_START_NUMBER = 1;

    /**
     * 로또 마지막 번호
     */
    public static final int LOTTO_END_NUMBER = 45;
    private List<Integer> numberList = new ArrayList<>();

    public LottoNumbers(List<Integer> numberList) {
        this.numberList = numberList;
    }

    /**
     * 랜덤 로또 번호를 생성합니다.
     */
    public LottoNumbers() {
        this.numberList = makeLottoNumber();
    }

    /**
     * 문자열이 들어오면 해당 문자열로 로또 번호를 만듭니다.
     *
     * @param lottoStr
     */
    public LottoNumbers(String lottoStr) {
        isEmpty(lottoStr);

        String[] splitStr = lottoStr.split(",");

        List<Integer> number = isCorrectRange(splitStr);

        isNumSix(number);

        isDuplicate(number);

        this.numberList = number;
    }

    /**
     * 로또 범위가 맞는지 확인합니다.
     *
     * @param splitStr
     * @return
     */
    private List<Integer> isCorrectRange(String[] splitStr) {
        List<Integer> number = new ArrayList<>();

        for (String s : splitStr) {
            int num = 0;

            try {
                num = Integer.parseInt(s.trim());
            } catch (NumberFormatException e) {
                throw new NumberFormatException("숫자가 아닌 값을 입력할 수 없습니다");
            }

            if (!(num <= LOTTO_END_NUMBER && num >= LOTTO_START_NUMBER)) {
                throw new RuntimeException("1 ~ 45 사이 값을 입력 해주세요");
            }

            number.add(Integer.parseInt(s.trim()));
        }
        return number;
    }

    /**
     * 중복된 로또 번호가 없는지 확인합니다.
     *
     * @param number
     */
    private void isDuplicate(List<Integer> number) {
        Set<Integer> set = new HashSet<>(number);
        if (set.size() != number.size()) {
            throw new RuntimeException("중복된 값은 입력할 수 없습니다");
        }
    }

    /**
     * 로또 번호가 6갠지 확인합니다.
     *
     * @param number
     */
    private void isNumSix(List<Integer> number) {
        if (number.size() != 6) {
            throw new RuntimeException("로또 번호는 6개여야 합니다");
        }
    }

    /**
     * 로또 번호가 비었는지 확인합니다.
     *
     * @param lottoStr
     */
    private static void isEmpty(String lottoStr) {
        if (lottoStr.isEmpty()) {
            throw new RuntimeException("로또 번호는 6개여야 합니다");
        }
    }

    /**
     * 로또 랜덤 번호를 생성합니다
     *
     * @return
     */
    private List<Integer> makeLottoNumber() {
        List<Integer> list = new ArrayList<>();
        for (int i = LottoNumbers.LOTTO_START_NUMBER; i <= LottoNumbers.LOTTO_END_NUMBER; i++) {
            list.add(i);
        }

        Collections.shuffle(list);
        List<Integer> lottoNumberList = list.subList(0, 6);
        Collections.sort(lottoNumberList);

        return lottoNumberList;
    }

    public List<Integer> getNumberList() {
        return numberList;
    }
}
