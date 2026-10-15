import java.util.*;

class Marker {
    public int minOperations(String s) {
        int n = s.length();
        int ans = Integer.MAX_VALUE;
        
        for (int r = 0; r < n; r++) {
            int ops = r;
            for (int i = 0; i < n / 2; i++) {
                int left = (r + i) % n;
                int right = (r + n - 1 - i) % n;
                char a = s.charAt(left);
                char b = s.charAt(right);
                int diff = Math.abs(a - b);
                ops += Math.min(diff, 26 - diff);
            }
            ans = Math.min(ans, ops);
        }
        return ans;
    }

    public boolean isCorrect(String s, int output) {
        return minOperations(s) == output;
    }
}