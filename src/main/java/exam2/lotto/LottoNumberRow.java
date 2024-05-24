package exam2.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoNumberRow {

    private List<LottoNumber> numberList = new ArrayList<>();

    public LottoNumberRow(List<Integer> numberList) {
        for (Integer num : numberList) {
            this.numberList.add(new LottoNumber(num));
        }
    }

    /**
     * 랜덤 로또 번호를 생성합니다.
     */
    public LottoNumberRow() {
        this.numberList = makeLottoNumber();
    }

    /**
     * 문자열이 들어오면 해당 문자열로 로또 번호를 만듭니다.
     *
     * @param inputNumbers
     */
    public LottoNumberRow(String inputNumbers) {
        if (inputNumbers.isEmpty()) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다");
        }

        String[] numbers = inputNumbers.split(",");

        List<LottoNumber> lottoNumbers = new ArrayList<>();

        for (String num : numbers) {
            lottoNumbers.add(new LottoNumber(num));
        }

        checkValidLength(lottoNumbers);

        checkDuplicateNumber(lottoNumbers);

        this.numberList = lottoNumbers;
    }

    /**
     * 중복된 로또 번호가 없는지 확인합니다.
     *
     * @param number
     */
    private void checkDuplicateNumber(List<LottoNumber> number) {
        Set<LottoNumber> set = new HashSet<>(number);
        if (set.size() != number.size()) {
            throw new IllegalArgumentException("중복된 값은 입력할 수 없습니다");
        }
    }

    /**
     * 로또 번호가 6갠지 확인합니다.
     *
     * @param number
     */
    private void checkValidLength(List<LottoNumber> number) {
        if (number.size() != 6) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다");
        }
    }

    public boolean checkContainsNumber(LottoNumber number) {
        return numberList.contains(number);
    }

    /**
     * 로또 랜덤 번호를 생성합니다
     *
     * @return
     */
    private List<LottoNumber> makeLottoNumber() {
        List<LottoNumber> list = new ArrayList<>();
        for (int i = LottoNumber.LOTTO_START_NUMBER; i <= LottoNumber.LOTTO_END_NUMBER; i++) {
            list.add(new LottoNumber(i));
        }

        Collections.shuffle(list);
        List<LottoNumber> lottoNumberList = list.subList(0, 6);
        Collections.sort(lottoNumberList);

        return lottoNumberList;
    }

    public List<LottoNumber> getNumberList() {
        return numberList;
    }

}
