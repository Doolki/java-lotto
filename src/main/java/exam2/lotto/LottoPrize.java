package exam2.lotto;

import java.util.Arrays;

public enum LottoPrize {
    ZERO(8, 0, 0, 0),
    ONE(7, 1, 0, 0),
    TWO(6, 2, 0, 0),
    THREE(5, 3, 0, 5_000),
    FOUR(4, 4, 0, 50_000),
    FIVE(3, 5, 0, 1_500_000),
    BONUS(2, 5, 1, 30_000_000),
    SIX(1, 6, 0, 2_000_000_000);

    private final int rank;
    private final int bonus;
    private final int match;
    private final int prize;

    LottoPrize(int rank, int match, int bonus, int prize) {
        this.rank = rank;
        this.match = match;
        this.prize = prize;
        this.bonus = bonus;
    }

    public int getRank() {
        return rank;
    }

    public int getMatch() {
        return match;
    }

    public int getPrize() {
        return prize;
    }

    public int getBonus() {
        return bonus;
    }

    public static int getPrize(int index) {
        return LottoPrize.values()[index].getPrize();
    }

    public static LottoPrize getLottoRank(int rank) {
        return Arrays.stream(LottoPrize.values())
            .filter((it) -> it.rank == rank)
            .findFirst()
            .orElse(LottoPrize.ZERO);
    }

    public static LottoPrize getLottoPrize(int match, int bonus) {
        return Arrays.stream(LottoPrize.values())
            .filter((it) -> it.equalMatch(match, bonus))
            .findFirst()
            .orElse(LottoPrize.ZERO);
    }

    public boolean equalMatch(int match, int bonus) {
        return this.match == match && this.bonus == bonus;
    }
}

