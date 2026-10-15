public class Marker {

    public int countKthRoots(int l, int r, int k) {
        if (k == 1) return r - l + 1;
        int cnt = 0;
        for (long x = 0; ; x++) {
            long p = 1;
            boolean over = false;
            for (int i = 0; i < k; i++) {
                p *= x;
                if (p > r) { over = true; break; }
            }
            if (over) break;
            if (p >= l) cnt++;
        }
        return cnt;
    }

    public boolean isCorrect(int l, int r, int k, int out) {
        return countKthRoots(l, r, k) == out;
    }
}
