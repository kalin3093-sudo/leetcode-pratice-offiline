import java.util.*;

class Marker {
    public long minMoves(int[] balance) {
        int n = balance.length;
        long sum = 0;
        int negIdx = -1;
        for (int i = 0; i < n; i++) {
            sum += balance[i];
            if (balance[i] < 0) negIdx = i;
        }
        if (sum < 0) return -1;
        if (negIdx == -1) return 0;

        List<Long> posVals = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i != negIdx && balance[i] > 0) {
                int dist = Math.min((i - negIdx + n) % n, (negIdx - i + n) % n);
                posVals.add(((long) balance[i] << 20) + dist);
            }
        }
        posVals.sort(null);

        long need = -balance[negIdx];
        long moves = 0;
        for (long val : posVals) {
            int b = (int) (val >> 20);
            int d = (int) (val & ((1 << 20) - 1));
            if (need <= 0) break;
            long take = Math.min(b, need);
            moves += take * d;
            need -= take;
        }
        return need > 0 ? -1 : moves;
    }

    public boolean isCorrect(int[] balance, long output) {
        return minMoves(balance) == output;
    }
}