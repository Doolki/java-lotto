package domain;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

class LottoRewordTest {

    @Test
    @DisplayName("로또의 당첨을 반환한다.")
    void test() {
        // given
        final LottoTicket lottoTicket = new LottoTicket(List.of(
                LottoNumbers.of(1, 3, 4, 5, 6, 7),
                LottoNumbers.of(1, 4, 5, 6, 7, 9),
                LottoNumbers.of(1, 3, 4, 5, 6, 7),
                LottoNumbers.of(1, 2, 4, 5, 6, 8)
        ));

        final LottoNumbers lottoNumbers = LottoNumbers.of(1, 2, 4, 5, 6, 8);


        // when
        final Map<LottoReword, Integer> lottoRewords = LottoReword.checkLottoRewords(lottoTicket, lottoNumbers);


        // then
        assertSoftly((it) -> {
            it.assertThat(lottoRewords).hasSize(2);
            it.assertThat(lottoRewords.get(LottoReword.FIRST_PLACE)).isEqualTo(1);
            it.assertThat(lottoRewords.get(LottoReword.THIRD_PLACE)).isEqualTo(3);
        });
    }
}
