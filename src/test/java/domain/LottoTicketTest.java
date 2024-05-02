package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;


import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class LottoTicketTest {

    @DisplayName("로또 번호들은 중복 되지 않는 값으로 이루어져 있다.")
    @Test
    void notduplicate() {
        LottoTicket lottoTicket = LottoTicket.createLottoTicket(1, 3, 4, 5, 6, 6, 7);

        assertThat(lottoTicket.getLottoNumbers()).hasSize(6);
    }

    @ParameterizedTest
    @DisplayName("로또 번호는 6자리 이어야 한다.")
    @MethodSource("로또_번호가_비어있거나_6자리가_아닌것")
    @NullAndEmptySource
    void lottoNumberIsThen6(int[] lottoNumbers) {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            LottoTicket.createLottoTicket(lottoNumbers);
        });
    }

    private static Stream<Arguments> 로또_번호가_비어있거나_6자리가_아닌것() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3, 4, 5}),
                Arguments.of(new int[]{1}),
                Arguments.of(new int[]{1, 2, 3, 4, 5, 6, 7})
        );
    }


}
