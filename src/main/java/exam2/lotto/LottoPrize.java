package exam2.lotto;

public enum LottoPrize {
    PRIZE(new int[]{0, 0, 0, 5000, 50000, 1500000, 2000000000});

    private final int[] prize;

    LottoPrize(int[] prize) {
        this.prize = prize;
    }

    public int[] getPrize() {
        return prize;
    }
}
