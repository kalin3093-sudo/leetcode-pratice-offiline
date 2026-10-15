import java.util.*;
class Marker {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) count[c - 'a']++;
        
        char[] ans = new char[n];
        if (build(0, count, target.toCharArray(), ans, true)) {
            String res = new String(ans);
            if (res.compareTo(target) > 0) return res;
        }
        return "";
    }
    
    private boolean build(int pos, int[] count, char[] target, char[] ans, boolean tight) {
        int n = target.length;
        if (pos == n) return true;
        
        if (tight) {
            // Try to match target[pos]
            int need = target[pos] - 'a';
            if (count[need] > 0) {
                ans[pos] = target[pos];
                count[need]--;
                if (build(pos + 1, count, target, ans, true)) return true;
                count[need]++;
            }
            // Try to place a character > target[pos]
            for (int c = need + 1; c < 26; c++) {
                if (count[c] > 0) {
                    ans[pos] = (char)('a' + c);
                    count[c]--;
                    if (fillSmallest(pos + 1, count, ans)) return true;
                    count[c]++;
                }
            }
            return false;
        } else {
            return fillSmallest(pos, count, ans);
        }
    }
    
    private boolean fillSmallest(int pos, int[] count, char[] ans) {
        int n = ans.length;
        if (pos == n) return true;
        for (int c = 0; c < 26; c++) {
            if (count[c] > 0) {
                ans[pos] = (char)('a' + c);
                count[c]--;
                if (fillSmallest(pos + 1, count, ans)) return true;
                count[c]++;
            }
        }
        return false;
    }

    public boolean isCorrect(String s, String target, String output) {
        return output.equals(lexGreaterPermutation(s, target));
    }
}