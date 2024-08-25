package exam2.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.InstanceOfAssertFactories.predicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("로또 행 테스트")
class LottoNumberRowTest {

    @DisplayName("로또 구입 가격은 1000원 이상 이여야 합니다")
    @Test
    void 로또_구입_가격은_1000원_이상이여야_합니다() {
        assertThatThrownBy(() -> {
            int price = 999;
            List<LottoNumberRow> manualLottoRows = new ArrayList<>();

            PurchaseTicket.createAutoLottoNumber(price, manualLottoRows);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("로또 구입 가격은 1000원 이상 이여야 합니다");

    }

    @DisplayName("구매 금액은 수동 로또 금액보다 커야합니다.")
    @Test
    void 구매_금액은_수동_로또_금액보다_커야합니다() {
        assertThatThrownBy(() -> {
            int price = 1500;
            List<LottoNumberRow> manualLottoRows = new ArrayList<>();
            manualLottoRows.add(new LottoNumberRow(Arrays.asList(1, 2, 3, 4, 5, 6)));
            manualLottoRows.add(new LottoNumberRow(Arrays.asList(1, 2, 3, 4, 5, 6)));

            PurchaseTicket.createAutoLottoNumber(price, manualLottoRows);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("구매 금액이 부족합니다");

    }

    @DisplayName("로또를 구입한 개수를 계산합니다")
    @Test
    void 로또를_구입한_개수를_계산합니다() {
        int price = 1113455;
        List<LottoNumberRow> manualLottoRows = new ArrayList<>();

        PurchaseTicket purchase = PurchaseTicket.createAutoLottoNumber(price, manualLottoRows);

        assertThat(purchase.getCount()).isEqualTo(1113);
    }

    @DisplayName("랜덤으로 생성된 로또 번호는 1~45 사이 값 입니다")
    @Test
    void 랜덤으로_생성된_로또번호는_1_45_사이_값_입니다() {
        LottoNumberRow lottoNumberRow = new LottoNumberRow();

        assertThat(lottoNumberRow.getNumberList()).allMatch(
            number -> number.getNumber() >= 1 && number.getNumber() <= 45);
    }

    @DisplayName("랜덤으로 생성된 로또 번호는 6개로 이루어져 있습니다")
    @Test
    void 랜덤으로_생성된_로또_번호는_6개로_이루어져_있습니다() {
        LottoNumberRow lottoNumberRow = new LottoNumberRow();

        assertThat(lottoNumberRow.getNumberList()).hasSize(6);
    }

    @DisplayName("로또 당첨 번호는 6개여야 합니다")
    @Test
    void 로또_당첨_번호는_6개여야_합니다() {
        assertThatThrownBy(() -> {
            new WinningNumber(new LottoNumberRow(Arrays.asList(1, 2, 3, 4)), 40);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("로또 번호는 6개여야 합니다");
    }

    @DisplayName("로또 당첨 번호는 1 ~ 45 사이 값이여야 합니다")
    @Test
    void 로또_당첨_번호는_1_45_사이_값이여야_합니다() {
        assertThatThrownBy(() -> {
            new WinningNumber(new LottoNumberRow(Arrays.asList(1, 2, 3, 4, 5, 100)), 40);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("로또번호는 1 ~ 45 사이 값 이어야 합니다");
    }

    @DisplayName("로또 당첨 번호는 중복될 수 없습니다")
    @Test
    void 로또_당첨_번호는_중복될_수_없습니다() {

        assertThatThrownBy(() -> {
            new WinningNumber(new LottoNumberRow(Arrays.asList(1, 2, 3, 4, 5, 1)), 30);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("중복된 값은 입력할 수 없습니다");
    }


    @DisplayName("로또 번호가 포함되었는지 확인합니다")
    @Test
    void checkContainsNumber() {
        //given
        LottoNumber number = new LottoNumber(2);
        LottoNumberRow lottoNumberRow = new LottoNumberRow(Arrays.asList(1, 2, 3, 4, 5, 6));

        //when
        Boolean isContain = lottoNumberRow.checkContainsNumber(number);

        //then
        assertThat(isContain).isEqualTo(Boolean.TRUE);
    }

    @DisplayName("일치하는 번호 개수 세기")
    @Test
    void matchCount() {
        //given
        LottoNumberRow winningNumberRow = new LottoNumberRow(Arrays.asList(1, 2, 13, 14, 15, 16));
        LottoNumberRow purchaseNumberRow = new LottoNumberRow(Arrays.asList(1, 2, 3, 4, 5, 6));

        //when
        int matchCount = purchaseNumberRow.matchCount(winningNumberRow);

        //then
        assertThat(matchCount).isEqualTo(2);
    }

    @DisplayName("일치하는 번호 개수 세기")
    @Test
    void matchCount2() {
        //given
        LottoNumber winningNumber = new LottoNumber(2);
        LottoNumberRow purchaseNumberRow = new LottoNumberRow(Arrays.asList(1, 2, 3, 4, 5, 6));

        //when
        int matchCount = purchaseNumberRow.matchCount(winningNumber);

        //then
        assertThat(matchCount).isEqualTo(1);
    }
}
