import java.util.*;
class Marker {
    public long maximumProfit(int[] prices, int k) {
        int n = prices.length;
        long NEG = Long.MIN_VALUE / 4;
        long[] flat = new long[k + 1], lp = new long[k + 1], sp = new long[k + 1];
        long[] nflat = new long[k + 1], nlp = new long[k + 1], nsp = new long[k + 1];
        Arrays.fill(flat, NEG);
        Arrays.fill(lp, NEG);
        Arrays.fill(sp, NEG);
        flat[0] = 0;
        for (int i = 0; i < n; i++) {
            int p = prices[i];
            Arrays.fill(nflat, NEG);
            Arrays.fill(nlp, NEG);
            Arrays.fill(nsp, NEG);
            for (int m = 0; m <= k; m++) {
                long best = flat[m];
                if (m > 0) {
                    if (lp[m - 1] != NEG) best = Math.max(best, lp[m - 1] + p);
                    if (sp[m - 1] != NEG) best = Math.max(best, sp[m - 1] - p);
                }
                nflat[m] = best;
                if (flat[m] != NEG) {
                    nlp[m] = flat[m] - p;
                    nsp[m] = flat[m] + p;
                }
                if (lp[m] != NEG) nlp[m] = Math.max(nlp[m], lp[m]);
                if (sp[m] != NEG) nsp[m] = Math.max(nsp[m], sp[m]);
            }
            long[] tf = flat, tl = lp, ts = sp;
            flat = nflat; lp = nlp; sp = nsp;
            nflat = tf; nlp = tl; nsp = ts;
        }
        long ans = 0;
        for (int m = 0; m <= k; m++) ans = Math.max(ans, flat[m]);
        return ans;
    }

    public boolean isCorrect(int[] prices, int k, long output) {
        return maximumProfit(prices, k) == output;
    }
}
