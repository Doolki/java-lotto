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

        checkDuplicateNumber(this.numberList);
        checkValidLength(this.numberList);
    }

    /**
     * 랜덤 로또 번호를 생성합니다.
     */
    public LottoNumberRow() {
        this.numberList = makeLottoNumber();
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

    /**
     * 일치하는 번호 개수 세기
     *
     * @param lottoNumberRow
     * @return
     */
    public int matchCount(LottoNumberRow lottoNumberRow) {
        return (int) this.numberList.stream()
            .filter(lottoNumberRow::checkContainsNumber).count();
    }

    /**
     * 일치하는 번호 개수 세기
     *
     * @param lottoNumber
     * @return
     */
    public int matchCount(LottoNumber lottoNumber) {
        return (int) this.numberList.stream()
            .filter(number -> number.equals(lottoNumber)).count();
    }

    public List<LottoNumber> getNumberList() {
        return numberList;
    }

}
