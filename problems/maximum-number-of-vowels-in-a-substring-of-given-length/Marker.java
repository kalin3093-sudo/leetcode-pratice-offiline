import java.util.*;

class Marker {
    public int maxVowels(String s, int k) {
        int n = s.length();
        int cnt = 0;
        for (int i = 0; i < k; i++) if (isVowel(s.charAt(i))) cnt++;
        int ans = cnt;
        for (int i = k; i < n; i++) {
            if (isVowel(s.charAt(i - k))) cnt--;
            if (isVowel(s.charAt(i))) cnt++;
            ans = Math.max(ans, cnt);
        }
        return ans;
    }
    
    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    public boolean isCorrect(String s, int k, int output) {
        return output == maxVowels(s, k);
    }
}