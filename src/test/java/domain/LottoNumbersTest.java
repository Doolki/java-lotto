package domain;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;


@DisplayName("로또 번호들 (일급 컬랙션) 테스트")
class LottoNumbersTest {

    @DisplayName("로또 번호들은 중복 되지 않는 값으로 이루어져 있다.")
    @Test
    void notDuplicate() {
        LottoNumbers lottoNumbers = LottoNumbers.of(1, 3, 4, 5, 6, 6, 7);

        assertSoftly((it) -> {
            it.assertThat(lottoNumbers.hasSize(6)).isTrue();
            it.assertThat(lottoNumbers.hasNotSize(6)).isFalse();
        });
    }

    @DisplayName("로또 번호가 같으면 로또 번호는 같다.")
    @Test
    void equalToLottoNumber() {
        LottoNumbers lottoNumbers = LottoNumbers.of(1, 2, 3, 4, 5, 6);
        LottoNumbers lottoNumbers2 = LottoNumbers.of(1, 2, 3, 4, 5, 6);

        assertThat(lottoNumbers).isEqualTo(lottoNumbers2);
    }

    @DisplayName("로또 번호가 다르면 로또 번호는 같지 않다.")
    @Test
    void notEqualToLottoNumber() {
        LottoNumbers lottoNumbers = LottoNumbers.of(1, 2, 3, 4, 5, 6);
        LottoNumbers lottoNumbers2 = LottoNumbers.of(8, 2, 3, 4, 5, 6);

        assertThat(lottoNumbers).isNotEqualTo(lottoNumbers2);
    }


}
