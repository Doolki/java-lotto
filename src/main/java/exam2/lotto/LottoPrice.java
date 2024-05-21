package exam2.lotto;

public enum LottoPrice {
    PRICE_1000(1_000);
    private final int price;

    LottoPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
