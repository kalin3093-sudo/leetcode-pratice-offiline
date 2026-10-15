import java.util.*;
class Marker {
    public int minDeletion(String s, int k) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) freq[c - 'a']++;
        List<Integer> counts = new ArrayList<>();
        for (int f : freq) if (f > 0) counts.add(f);
        Collections.sort(counts);
        int distinct = counts.size();
        if (distinct <= k) return 0;
        int toRemove = distinct - k;
        int ans = 0;
        for (int i = 0; i < toRemove; i++) ans += counts.get(i);
        return ans;
    }

    public boolean isCorrect(String s, int k, int output) {
        int expected = minDeletion(s, k);
        return expected == output;
    }
}