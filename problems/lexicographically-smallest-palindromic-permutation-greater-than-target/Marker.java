import java.util.*;
class Marker {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) count[c - 'a']++;
        
        int oddCount = 0;
        for (int c : count) if (c % 2 == 1) oddCount++;
        if (oddCount > 1) return "";
        
        char[] ans = new char[n];
        if (dfs(0, n - 1, count, target.toCharArray(), ans, true, false)) {
            return new String(ans);
        }
        return "";
    }
    
    // tight: prefix exactly matches target[0..l-1]
    // gt: prefix is already strictly greater than target[0..l-1]
    private boolean dfs(int l, int r, int[] count, char[] target, char[] ans, boolean tight, boolean gt) {
        if (l > r) {
            return gt; // must be strictly greater overall
        }
        if (l == r) {
            for (int c = 0; c < 26; c++) {
                if (count[c] > 0) {
                    char ch = (char)('a' + c);
                    if (tight && ch < target[l]) continue;
                    boolean nextTight = tight && ch == target[l];
                    boolean nextGt = gt || (tight && ch > target[l]);
                    ans[l] = ch;
                    count[c]--;
                    if (dfs(l + 1, r - 1, count, target, ans, nextTight, nextGt)) return true;
                    count[c]++;
                }
            }
            return false;
        }
        
        for (int c = 0; c < 26; c++) {
            if (count[c] < 2) continue;
            char ch = (char)('a' + c);
            if (tight && ch < target[l]) continue;
            
            ans[l] = ans[r] = ch;
            count[c] -= 2;
            
            boolean nextTight = tight && ch == target[l];
            boolean nextGt = gt || (tight && ch > target[l]);
            
            if (nextGt) {
                if (fillSmallest(l + 1, r - 1, count, ans)) return true;
            } else {
                if (dfs(l + 1, r - 1, count, target, ans, nextTight, nextGt)) return true;
            }
            
            count[c] += 2;
        }
        return false;
    }
    
    private boolean fillSmallest(int l, int r, int[] count, char[] ans) {
        if (l > r) return true;
        if (l == r) {
            for (int c = 0; c < 26; c++) {
                if (count[c] > 0) {
                    ans[l] = (char)('a' + c);
                    count[c]--;
                    if (fillSmallest(l + 1, r - 1, count, ans)) return true;
                    count[c]++;
                }
            }
            return false;
        }
        for (int c = 0; c < 26; c++) {
            if (count[c] >= 2) {
                char ch = (char)('a' + c);
                ans[l] = ans[r] = ch;
                count[c] -= 2;
                if (fillSmallest(l + 1, r - 1, count, ans)) return true;
                count[c] += 2;
            }
        }
        return false;
    }

    public boolean isCorrect(String s, String target, String output) {
        return output.equals(lexPalindromicPermutation(s, target));
    }
}