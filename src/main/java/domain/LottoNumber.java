package domain;

import java.util.*;

public class LottoNumber {
    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 45;
    private static final Map<Integer, LottoNumber> cache = new HashMap<>();

    private final int number;

    static {
        for (int i = MIN_NUMBER; i <= MAX_NUMBER; i++) {
            cache.put(i, new LottoNumber(i));
        }
    }

    private LottoNumber(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 " + MIN_NUMBER + "부터" + MAX_NUMBER + "이어야 합니다.");
        }
        this.number = number;
    }

    public static LottoNumber of(int number) {
        LottoNumber lottoNumber = cache.get(number);
        if (lottoNumber == null) {
            throw new IllegalArgumentException("로또 번호는 " + MIN_NUMBER + "부터" + MAX_NUMBER + "이어야 합니다.");
        }
        return lottoNumber;
    }

    public static List<LottoNumber> getAllLottoNumberList() {
        return new ArrayList<>(cache.values());
    }

    public int getValue() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return number;
    }
}
