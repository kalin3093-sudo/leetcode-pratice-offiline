import java.util.*;
class Marker {
    public String lexSmallestAfterDeletion(String s) {
        int n = s.length();
        int[] totalCount = new int[26];
        for (char c : s.toCharArray()) totalCount[c - 'a']++;
        
        // Characters that must appear at least once
        boolean[] mustInclude = new boolean[26];
        int mustCount = 0;
        for (int i = 0; i < 26; i++) {
            if (totalCount[i] > 0) {
                mustInclude[i] = true;
                mustCount++;
            }
        }
        boolean[] included = new boolean[26];
        
        StringBuilder ans = new StringBuilder();
        int pos = 0;
        int[] remaining = totalCount.clone();
        
        while (pos < n && mustCount > 0) {
            boolean found = false;
            for (int c = 0; c < 26; c++) {
                int p = s.indexOf((char)('a' + c), pos);
                if (p == -1) continue;
                
                // Check if all mustInclude chars appear in s[p..n-1]
                boolean ok = true;
                for (int d = 0; d < 26; d++) {
                    if (mustInclude[d] && !included[d] && s.indexOf((char)('a' + d), p) == -1) {
                        ok = false;
                        break;
                    }
                }
                if (!ok) continue;
                
                // We can pick c at position p
                ans.append((char)('a' + c));
                // Update counts from pos to p
                for (int i = pos; i <= p; i++) {
                    int ch = s.charAt(i) - 'a';
                    remaining[ch]--;
                }
                if (mustInclude[c] && !included[c]) {
                    included[c] = true;
                    mustCount--;
                }
                pos = p + 1;
                found = true;
                break;
            }
            if (!found) break;
        }
        
        return ans.toString();
    }

    public boolean isCorrect(String s, String output) {
        return output.equals(lexSmallestAfterDeletion(s));
    }
}