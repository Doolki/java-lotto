package domain;


import java.util.Set;

public class LottoNumbers {

    private final Set<LottoNumber> values;

    public LottoNumbers(Set<LottoNumber> values) {
        this.values = values;
    }

    public Set<LottoNumber> getValues() {
        return values;
    }
}
