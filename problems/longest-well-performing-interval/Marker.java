import java.util.*;
class Marker {
    public int longestWPI(int[] hours) {
        int n = hours.length;
        int[] pref = new int[n + 1];
        for (int i = 0; i < n; i++) {
            pref[i + 1] = pref[i] + (hours[i] > 8 ? 1 : -1);
        }
        
        int[] stack = new int[n + 1];
        int top = 0;
        for (int i = 0; i <= n; i++) {
            if (top == 0 || pref[i] < pref[stack[top - 1]]) {
                stack[top++] = i;
            }
        }
        
        int ans = 0;
        for (int j = n; j >= 0; j--) {
            while (top > 0 && pref[j] > pref[stack[top - 1]]) {
                ans = Math.max(ans, j - stack[top - 1]);
                top--;
            }
        }
        return ans;
    }

    public boolean isCorrect(int[] hours, int output) {
        return longestWPI(hours) == output;
    }
}