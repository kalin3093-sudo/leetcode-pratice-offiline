import java.util.*;

class Marker {
    private static final int MOD = 1_000_000_007;

    public int countWays(int[][] ranges) {
        int n = ranges.length;
        int[][] sorted = ranges.clone();
        Arrays.sort(sorted, Comparator.comparingInt(a -> a[0]));

        int components = 0;
        int currEnd = -1;
        for (int[] r : sorted) {
            if (r[0] > currEnd) {
                components++;
                currEnd = r[1];
            } else {
                currEnd = Math.max(currEnd, r[1]);
            }
        }

        long ans = 1;
        for (int i = 0; i < components; i++) {
            ans = ans * 2 % MOD;
        }
        return (int) ans;
    }

    public boolean isCorrect(int[][] ranges, int output) {
        return output == countWays(ranges);
    }
}