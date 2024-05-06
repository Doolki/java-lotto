package domain;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.SoftAssertions.assertSoftly;


@DisplayName("로또 번호 테스트")
class LottoNumberTest {

    @ParameterizedTest
    @DisplayName("로또 넘버는 1부터 45이어야 합니다.")
    @ValueSource(ints = {0, -1, 46})
    void lottoNumber_is_validateNumber(int number) throws Exception {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> LottoNumber.of(number));
    }

    @Test
    @DisplayName("로또번호가 같으면 같다.")
    void equalToLottoNumber() {
        assertThat(LottoNumber.of(1)).isEqualTo(LottoNumber.of(1));
    }



    @Test
    @DisplayName("로또번호 전체를 리스트를 반환한다.")
    void getAllLottoNumberList() {
        List<LottoNumber> allLottoNumberList = LottoNumber.getAllLottoNumberList();

        assertSoftly((it) -> {
            it.assertThat(allLottoNumberList).hasSize(LottoNumber.MAX_NUMBER);
            it.assertThat(allLottoNumberList).contains(LottoNumber.of(LottoNumber.MIN_NUMBER), LottoNumber.of(LottoNumber.MAX_NUMBER));
        });
    }

}
