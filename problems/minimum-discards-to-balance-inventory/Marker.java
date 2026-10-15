import java.util.*;
class Marker {
    public int minArrivalsToDiscard(int[] arrivals, int w, int m) {
        int n = arrivals.length;
        Map<Integer, Integer> cnt = new HashMap<>();
        boolean[] kept = new boolean[n];
        int discarded = 0;

        for (int i = 0; i < n; i++) {
            // Remove day i-w if it was kept
            if (i >= w && kept[i - w]) {
                int type = arrivals[i - w];
                cnt.put(type, cnt.get(type) - 1);
            }
            int type = arrivals[i];
            int current = cnt.getOrDefault(type, 0);
            if (current < m) {
                // Keep it
                kept[i] = true;
                cnt.put(type, current + 1);
            } else {
                // Discard it
                kept[i] = false;
                discarded++;
            }
        }
        return discarded;
    }

    public boolean isCorrect(int[] arrivals, int w, int m, int output) {
        int expected = minArrivalsToDiscard(arrivals, w, m);
        return expected == output;
    }
}