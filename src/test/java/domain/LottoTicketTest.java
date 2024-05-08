package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("로또 티켓 테스트")
class LottoTicketTest {

    @Test
    void test() {

    }




//    @ParameterizedTest
//    @DisplayName("로또 번호는 6자리 이어야 한다.")
//    @MethodSource("로또_번호가_비어있거나_6자리가_아닌것")
//    @NullAndEmptySource
//    void lottoNumberIsThen6(int[] lottoNumbers) {
//        assertThatIllegalArgumentException().isThrownBy(() -> {
//            LottoTicket.createLottoTicket(LottoNumbers.of(lottoNumbers));
//        });
//    }
//
//    private static Stream<Arguments> 로또_번호가_비어있거나_6자리가_아닌것() {
//        return Stream.of(
//                Arguments.of(new int[]{1, 2, 3, 4, 5}),
//                Arguments.of(new int[]{1}),
//                Arguments.of(new int[]{1, 2, 3, 4, 5, 6, 7})
//        );
//    }


}
