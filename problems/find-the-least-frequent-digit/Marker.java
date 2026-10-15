import java.util.*;
class Marker {
    public int getLeastFrequentDigit(int n) {
        int[] cnt = new int[10];
        while (n > 0) {
            cnt[n % 10]++;
            n /= 10;
        }
        int minFreq = Integer.MAX_VALUE;
        int ans = 0;
        for (int d = 0; d < 10; d++) {
            if (cnt[d] > 0 && cnt[d] < minFreq) {
                minFreq = cnt[d];
                ans = d;
            }
        }
        return ans;
    }

    public boolean isCorrect(int n, int output) {
        return getLeastFrequentDigit(n) == output;
    }
}