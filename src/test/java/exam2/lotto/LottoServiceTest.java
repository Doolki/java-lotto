package exam2.lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoServiceTest {

    @DisplayName("로또 당첨 개수와 비율을 계산합니다")
    @Test
    void 로또_당첨_개수와_비율을_계산합니다() {
        WinningNumber winningNumbers = new WinningNumber("1,2,3,4,5,6");

        List<LottoNumbers> lottoNumberList = new ArrayList<>();
        lottoNumberList.add(new LottoNumbers(Arrays.asList(8, 21, 23, 41, 42, 43)));
        lottoNumberList.add(new LottoNumbers(Arrays.asList(3, 5, 11, 16, 32, 38)));
        lottoNumberList.add(new LottoNumbers(Arrays.asList(7, 11, 16, 35, 36, 44)));
        lottoNumberList.add(new LottoNumbers(Arrays.asList(1, 8, 11, 31, 41, 42)));
        lottoNumberList.add(new LottoNumbers(Arrays.asList(13, 14, 16, 38, 42, 45)));
        lottoNumberList.add(new LottoNumbers(Arrays.asList(7, 11, 30, 40, 42, 43)));
        lottoNumberList.add(new LottoNumbers(Arrays.asList(2, 13, 22, 32, 38, 45)));
        lottoNumberList.add(new LottoNumbers(Arrays.asList(23, 25, 33, 36, 39, 41)));
        lottoNumberList.add(new LottoNumbers(Arrays.asList(1, 3, 5, 14, 22, 45)));
        lottoNumberList.add(new LottoNumbers(Arrays.asList(5, 9, 38, 41, 43, 44)));
        lottoNumberList.add(new LottoNumbers(Arrays.asList(2, 8, 9, 18, 19, 21)));
        lottoNumberList.add(new LottoNumbers(Arrays.asList(13, 14, 18, 21, 23, 35)));
        lottoNumberList.add(new LottoNumbers(Arrays.asList(17, 21, 29, 37, 42, 45)));
        lottoNumberList.add(new LottoNumbers(Arrays.asList(3, 8, 27, 30, 35, 44)));

        PurchaseTicket purchase = new PurchaseTicket(lottoNumberList);

        LottoService lottoService = new LottoService();

        lottoService.calculateEqualListAndRate(purchase, winningNumbers);

        double expect = 0.35;

        assertThat(purchase.getRate()).isEqualTo(expect);
    }

}