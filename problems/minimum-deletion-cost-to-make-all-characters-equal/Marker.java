import java.util.*;
class Marker {
    public long minCost(String s, int[] cost) {
        long[] charSum = new long[26];
        long total = 0;
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            charSum[c] += cost[i];
            total += cost[i];
        }
        long maxKeep = 0;
        for (long sum : charSum) {
            if (sum > maxKeep) maxKeep = sum;
        }
        return total - maxKeep;
    }

    public boolean isCorrect(String s, int[] cost, long output) {
        long expected = minCost(s, cost);
        return expected == output;
    }
}