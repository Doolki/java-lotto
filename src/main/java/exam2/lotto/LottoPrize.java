package exam2.lotto;

import java.util.Arrays;

public enum LottoPrize {
    ZERO(8, 0, 0, 0),
    SEVENTH(7, 1, 0, 0),
    SIXTH(6, 2, 0, 0),
    FIFTH(5, 3, 0, 5_000),
    FOURTH(4, 4, 0, 50_000),
    THIRD(3, 5, 0, 1_500_000),
    SECOND(2, 5, 1, 30_000_000),
    FIRST(1, 6, 0, 2_000_000_000);

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

