package Exam2;

public class Lotto {

    private static int LOTTO_PRICE = 1000;

    public int purchaseCount(int price) {

        if (price == 0) {
            throw new RuntimeException("로또 구입 가격은 0이 될 수 없습니다.");
        }

        return (int) (price/LOTTO_PRICE);
    }

}
