import java.util.*;
class Marker {
    public int[] longestCommonPrefix(String[] words) {
        int n = words.length;
        if (n <= 1) return new int[0];
        
        int[] lcp = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            lcp[i] = lcp(words[i], words[i + 1]);
        }
        
        int[] prefixMax = new int[n - 1];
        prefixMax[0] = lcp[0];
        for (int i = 1; i < n - 1; i++) {
            prefixMax[i] = Math.max(prefixMax[i - 1], lcp[i]);
        }
        
        int[] suffixMax = new int[n - 1];
        suffixMax[n - 2] = lcp[n - 2];
        for (int i = n - 3; i >= 0; i--) {
            suffixMax[i] = Math.max(suffixMax[i + 1], lcp[i]);
        }
        
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int best = 0;
            if (i >= 2) best = Math.max(best, prefixMax[i - 2]);
            if (i < n - 2) best = Math.max(best, suffixMax[i + 1]);
            if (i > 0 && i < n - 1) {
                best = Math.max(best, lcp(words[i - 1], words[i + 1]));
            }
            ans[i] = best;
        }
        return ans;
    }
    
    private int lcp(String a, String b) {
        int len = Math.min(a.length(), b.length());
        int i = 0;
        while (i < len && a.charAt(i) == b.charAt(i)) i++;
        return i;
    }

    public boolean isCorrect(String[] words, int[] output) {
        if (output == null) return false;
        int[] expected = longestCommonPrefix(words);
        return Arrays.equals(expected, output);
    }
}