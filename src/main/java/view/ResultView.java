package view;

import domain.LottoNumber;
import domain.LottoNumbers;
import domain.LottoTicket;

import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

public class ResultView {

    public static void buyTicketSize(int size) {
        System.out.println(size + "개를 구매했습니다.");
    }

    public static void ticketInfo(LottoTicket ticket) {
        List<LottoNumbers> lottoNumbersList = ticket.getLottoNumbers();

        for (LottoNumbers lottoNumbers : lottoNumbersList) {
            printLottoNumbers(lottoNumbers.getValues());
        }
    }


    public static void printLottoNumbers(Set<LottoNumber> lottoNumbers) {
        StringBuilder printText = new StringBuilder("[");

        StringJoiner joiner = new StringJoiner(",");
        for (LottoNumber lottoNumber : lottoNumbers) {
            joiner.add(String.valueOf(lottoNumber.getValue()));
        }

        System.out.print(printText.append(joiner)
                .append("]")
                .append("\n"));
    }



}
