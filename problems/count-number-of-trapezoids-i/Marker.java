import java.util.*;

public class Marker {
    static final long MOD = 1_000_000_007L;

    public int countTrapezoids(int[][] points) {
        Map<Long, Long> cnt = new HashMap<>();
        for (int[] p : points) cnt.merge((long) p[1], 1L, Long::sum);
        long s = 0, s2 = 0;
        for (long c : cnt.values()) {
            long comb = c * (c - 1) / 2 % MOD;
            s = (s + comb) % MOD;
            s2 = (s2 + comb * comb % MOD) % MOD;
        }
        long res = ((s * s % MOD - s2) % MOD + MOD) % MOD;
        res = res * 500000004L % MOD;
        return (int) res;
    }

    public boolean isCorrect(int[][] points, int out) {
        return countTrapezoids(points) == out;
    }
}
