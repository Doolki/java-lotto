package exam2.view;

import exam2.lotto.LottoNumber;
import exam2.lotto.LottoNumberRow;
import java.nio.channels.InterruptedByTimeoutException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    public int purchasePrice() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public int purchaseManualCount() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public List<LottoNumberRow> purchaseManualLotto(int count) {
        Scanner scanner = new Scanner(System.in);

        List<LottoNumberRow> manualLottoRows = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String input = scanner.nextLine();
            String[] num = input.split(",");

            manualLottoRows.add(
                new LottoNumberRow(Arrays.stream(num).map(String::trim).map(Integer::parseInt)
                    .collect(Collectors.toList())));
        }

        return manualLottoRows;
    }

    public LottoNumberRow winningNumber() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] num = input.split(",");

        return new LottoNumberRow(Arrays.stream(num).map(String::trim).map(Integer::parseInt)
            .collect(Collectors.toList()));
    }

    public int bonusNumber() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

}
