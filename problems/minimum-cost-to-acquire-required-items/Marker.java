import java.util.*;
class Marker {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long bothCost = Math.min(costBoth, (long)cost1 + cost2);
        long rem1Cost = Math.min(costBoth, cost1);
        long rem2Cost = Math.min(costBoth, cost2);
        long common = Math.min(need1, need2);
        long rem1 = need1 - common;
        long rem2 = need2 - common;
        return common * bothCost + rem1 * rem1Cost + rem2 * rem2Cost;
    }

    public boolean isCorrect(int cost1, int cost2, int costBoth, int need1, int need2, long output) {
        long expected = minimumCost(cost1, cost2, costBoth, need1, need2);
        return expected == output;
    }
}