package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class LottoNumbersTest {

    @DisplayName("로또 넘버들은 중복 되지 않는 값으로 이루어져있다.")
    @Test
    void notduplicate() {
        LottoNumbers lottoNumbers = new LottoNumbers(Set.of(LottoNumber.of(1), LottoNumber.of(1), LottoNumber.of(2)));

        Set<LottoNumber> values = lottoNumbers.getValues();

        assertThat(values).hasSize(2);
    }


}
