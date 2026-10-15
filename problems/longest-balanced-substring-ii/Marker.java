import java.util.*;
class Marker {
    public int longestBalanced(String s) {
        int n = s.length();
        int ans = 0;
        
        // Case 1: single character (longest run)
        int cur = 1;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == s.charAt(i - 1)) cur++;
            else cur = 1;
            ans = Math.max(ans, cur);
        }
        
        // Prefix counts
        int[] prefA = new int[n + 1];
        int[] prefB = new int[n + 1];
        int[] prefC = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefA[i + 1] = prefA[i] + (s.charAt(i) == 'a' ? 1 : 0);
            prefB[i + 1] = prefB[i] + (s.charAt(i) == 'b' ? 1 : 0);
            prefC[i + 1] = prefC[i] + (s.charAt(i) == 'c' ? 1 : 0);
        }
        
        // Case 2: two distinct characters
        // For pair (a,b): balanced when count_a == count_b, c can be anything but must be 0 in substring
        // So we need substrings with no c, and prefA[j]-prefA[i] == prefB[j]-prefB[i]
        // => (prefA[j]-prefB[j]) == (prefA[i]-prefB[i]) and no c in between
        // We can split by 'c' segments
        ans = Math.max(ans, solveTwoChars(s, 'a', 'b'));
        ans = Math.max(ans, solveTwoChars(s, 'a', 'c'));
        ans = Math.max(ans, solveTwoChars(s, 'b', 'c'));
        
        // Case 3: all three characters
        Map<String, Integer> map = new HashMap<>();
        map.put("0,0", 0);
        for (int i = 1; i <= n; i++) {
            int a = prefA[i], b = prefB[i], c = prefC[i];
            String key = (b - a) + "," + (c - a);
            if (map.containsKey(key)) {
                ans = Math.max(ans, i - map.get(key));
            } else {
                map.put(key, i);
            }
        }
        
        return ans;
    }
    
    private int solveTwoChars(String s, char x, char y) {
        // Find longest substring with only x and y, where count_x == count_y
        int n = s.length();
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int diff = 0;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c != x && c != y) {
                // Reset
                map.clear();
                map.put(0, i);
                diff = 0;
                continue;
            }
            if (c == x) diff++;
            else diff--;
            if (map.containsKey(diff)) {
                ans = Math.max(ans, i - map.get(diff));
            } else {
                map.put(diff, i);
            }
        }
        return ans;
    }

    public boolean isCorrect(String s, int output) {
        return output == longestBalanced(s);
    }
}