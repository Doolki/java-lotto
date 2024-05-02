package exam2.lotto;

public enum LottoPrize {
    ZERO(0),
    ONE(0),
    TWO(0),
    THREE(5000),
    FOUR(50000),
    FIVE(1500000),
    SIX(2000000000);

    private final int prize;

    LottoPrize(int prize) {
        this.prize = prize;
    }

    public int getPrize() {
        return prize;
    }
}

