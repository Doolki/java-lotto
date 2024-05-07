package domain;

import java.util.*;
import java.util.stream.Collectors;

public class LottoNumbers {
    public static final int LOTTO_NUMBER_SIZE = 6;

    private final Set<LottoNumber> values;

    private LottoNumbers(Collection<LottoNumber> values) {
        if (values == null || values.isEmpty()) {
            throw new IllegalArgumentException("로또 번호는 1개 이상이어야 합니다.");
        }

        if (values.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException("로또 번호는 " + LOTTO_NUMBER_SIZE + "이어야 합니다.");
        }

        this.values = new HashSet<>(values);
    }

    private LottoNumbers(int[] values) {
        this(mapToLottoNumberSet(values));
    }

    private static Set<LottoNumber> mapToLottoNumberSet(int[] values) {
        if (values == null) {
            throw new IllegalArgumentException("로또 번호는 비어 있으면 안됩니다.");
        }

        return Arrays.stream(values)
                .mapToObj(LottoNumber::of)
                .collect(Collectors.toSet());
    }

    public static LottoNumbers generateAutoLottoNumbers() {
        List<LottoNumber> numberList = LottoNumber.getAllLottoNumberList();
        Collections.shuffle(numberList);
        return of(numberList.subList(0, LOTTO_NUMBER_SIZE));
    }

    public static LottoNumbers of(List<LottoNumber> numbers) {
        return new LottoNumbers(numbers);
    }

    public static LottoNumbers of(int... values) {
        return new LottoNumbers(values);
    }

    public static LottoNumbers of(String[] values) {
        return new LottoNumbers(Arrays.stream(values)
                .mapToInt(Integer::parseInt)
                .toArray());
    }


    public int matchCount(LottoNumbers lottoNumbers) {
        return (int) values.stream()
                .filter(lottoNumbers::isContainLottoNumber)
                .count();
    }

    private boolean isContainLottoNumber(LottoNumber lottoNumber) {
        return values.contains(lottoNumber);
    }

    public int size() {
        return values.size();
    }


    public Set<LottoNumber> getValues() {
        return values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LottoNumbers that = (LottoNumbers) o;
        return Objects.equals(values, that.values);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(values);
    }
}
