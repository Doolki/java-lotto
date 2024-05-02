package domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class LottoNumbers {

    private final Set<LottoNumber> values;

    private LottoNumbers(Set<LottoNumber> values) {
        if (values == null || values.isEmpty()) {
            throw new IllegalArgumentException("로또 번호는 1개 이상이어야 합니다.");
        }
        this.values = values;
    }

    public static LottoNumbers of(Set<LottoNumber> values) {
        return new LottoNumbers(values);
    }

    public static LottoNumbers of(int... values) {
        if (values == null) {
            throw new IllegalArgumentException("로또 번호는 비어 있으면 안됩니다.");
        }

        Set<LottoNumber> lottoNumbers = new HashSet<>();
        for (int value : values) {
            lottoNumbers.add(LottoNumber.of(value));
        }
        
        return new LottoNumbers(lottoNumbers);
    }

    public boolean hasSize(int size) {
        return values.size() == size;
    }

    public boolean hasNotSize(int size) {
        return !hasSize(size);
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
