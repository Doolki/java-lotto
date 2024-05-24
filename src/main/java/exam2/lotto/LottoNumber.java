package exam2.lotto;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {

    /**
     * 로또 시작 번호
     */
    public static final int LOTTO_START_NUMBER = 1;

    /**
     * 로또 마지막 번호
     */
    public static final int LOTTO_END_NUMBER = 45;

    private int number;


    /**
     * 로또 번호 유효성 체크
     *
     * @param number
     */
    public LottoNumber(String number) {
        this.number = checkValidRange(number);
    }

    public LottoNumber(int number) {
        this.number = checkValidRange(number);
    }

    /**
     * 로또 범위가 맞는지 확인합니다.
     *
     * @param inputNumber
     * @return
     */
    public int checkValidRange(String inputNumber) {
        int lottoNumber = 0;

        try {
            lottoNumber = Integer.parseInt(inputNumber.trim());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("숫자가 아닌 값을 입력할 수 없습니다");
        }

        if (!(lottoNumber <= LOTTO_END_NUMBER && lottoNumber >= LOTTO_START_NUMBER)) {
            throw new IllegalArgumentException("1 ~ 45 사이 값을 입력 해주세요");
        }

        return Integer.parseInt(inputNumber.trim());
    }

    public int checkValidRange(int inputNumber) {
        if (!(inputNumber <= LOTTO_END_NUMBER && inputNumber >= LOTTO_START_NUMBER)) {
            throw new IllegalArgumentException("1 ~ 45 사이 값을 입력 해주세요");
        }

        return inputNumber;
    }


    public int getNumber() {
        return number;
    }

    @Override
    public int compareTo(LottoNumber other) {
        return Integer.compare(this.number, other.number);
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoNumber that = (LottoNumber) o;
        return getNumber() == that.getNumber();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber());
    }
}
