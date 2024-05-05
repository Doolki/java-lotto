package domain;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

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


    @DisplayName("로또 번호들을 카운팅한다")
    @ParameterizedTest
    @MethodSource("로또번호들끼리의_매칭되는_수")
    void matchCount(LottoNumbers source, LottoNumbers target, int matchCount) {
        assertThat(source.matchCount(target)).isEqualTo(matchCount);
    }


    private static Stream<Arguments> 로또번호들끼리의_매칭되는_수() {
        return Stream.of(
                Arguments.of(LottoNumbers.of(1, 2, 3, 4, 5, 6), LottoNumbers.of(1, 2, 3, 4, 5, 6), 6),
                Arguments.of(LottoNumbers.of(1, 2, 3, 4, 5, 6), LottoNumbers.of(1, 2, 3, 4, 5, 7), 5),
                Arguments.of(LottoNumbers.of(1, 2, 3, 4, 5, 6), LottoNumbers.of(44, 2, 3, 4, 5, 8), 4),
                Arguments.of(LottoNumbers.of(1, 2, 3, 4, 5, 6), LottoNumbers.of(44, 43, 42, 41, 40, 39), 0)
        );
    }
}
