import java.util.*;
class Marker {
    public int minTime(String s, int[] order, int k) {
        int n = s.length();
        if (k <= 0) return 0;
        if (k > n * (n + 1L) / 2) return -1;
        
        int left = 0, right = n - 1, ans = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (isActive(s, order, k, mid)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
    
    private boolean isActive(String s, int[] order, int k, int t) {
        boolean[] starred = new boolean[s.length()];
        for (int i = 0; i <= t; i++) {
            starred[order[i]] = true;
        }
        
        long totalSubstrings = (long) s.length() * (s.length() + 1) / 2;
        long noStarSubstrings = 0;
        int len = 0;
        for (boolean star : starred) {
            if (!star) len++;
            else {
                noStarSubstrings += (long) len * (len + 1) / 2;
                len = 0;
            }
        }
        noStarSubstrings += (long) len * (len + 1) / 2;
        
        return totalSubstrings - noStarSubstrings >= k;
    }

    public boolean isCorrect(String s, int[] order, int k, int output) {
        return minTime(s, order, k) == output;
    }
}