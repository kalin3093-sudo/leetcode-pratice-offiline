import java.util.*;

class Marker {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, n);
        Arrays.fill(last, -1);
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            first[c] = Math.min(first[c], i);
            last[c] = Math.max(last[c], i);
        }
        
        List<int[]> valid = new ArrayList<>();
        for (int c = 0; c < 26; c++) {
            if (last[c] == -1) continue;
            int l = first[c], r = last[c];
            boolean ok = true;
            for (int i = l; i <= r; i++) {
                int d = s.charAt(i) - 'a';
                if (first[d] < l || last[d] > r) {
                    ok = false;
                    break;
                }
            }
            if (ok) valid.add(new int[]{l, r});
        }
        
        valid.sort(Comparator.comparingInt(a -> a[1] - a[0]));
        
        List<int[]> chosen = new ArrayList<>();
        for (int[] seg : valid) {
            boolean overlap = false;
            for (int[] c : chosen) {
                if (!(seg[1] < c[0] || seg[0] > c[1])) {
                    overlap = true;
                    break;
                }
            }
            if (!overlap) chosen.add(seg);
        }
        
        List<String> ans = new ArrayList<>();
        for (int[] seg : chosen) {
            ans.add(s.substring(seg[0], seg[1] + 1));
        }
        return ans;
    }

    public boolean isCorrect(String s, List<String> output) {
        List<String> expected = maxNumOfSubstrings(s);
        if (output.size() != expected.size()) return false;
        Set<String> outSet = new HashSet<>(output);
        return outSet.containsAll(expected);
    }
}