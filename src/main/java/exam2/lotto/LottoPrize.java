package exam2.lotto;

public enum LottoPrize {
    ZERO(0, 0),
    ONE(1, 0),
    TWO(2, 0),
    THREE(3, 5_000),
    FOUR(4, 50_000),
    FIVE(5, 1_500_000),
    SIX(6, 2_000_000_000);

    private final int index;
    private final int prize;

    LottoPrize(int index, int prize) {
        this.index = index;
        this.prize = prize;
    }

    public int getIndex() {
        return index;
    }

    public int getPrize() {
        return prize;
    }

    public static int getPrize(int index) {
        return LottoPrize.values()[index].getPrize();
    }

    public static LottoPrize getLottoPrize(int index) {
        return LottoPrize.values()[index];
    }
}

