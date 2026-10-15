import java.util.*;

class Marker {
    public int maxLength(List<String> arr) {
        List<Integer> masks = new ArrayList<>();
        List<Integer> lens = new ArrayList<>();
        for (String s : arr) {
            int mask = 0;
            boolean ok = true;
            for (char c : s.toCharArray()) {
                int bit = c - 'a';
                if ((mask & (1 << bit)) != 0) {
                    ok = false;
                    break;
                }
                mask |= 1 << bit;
            }
            if (ok) {
                masks.add(mask);
                lens.add(s.length());
            }
        }
        Map<Integer, Integer> dp = new HashMap<>();
        dp.put(0, 0);
        for (int i = 0; i < masks.size(); i++) {
            int m = masks.get(i), l = lens.get(i);
            Map<Integer, Integer> ndp = new HashMap<>(dp);
            for (Map.Entry<Integer, Integer> e : dp.entrySet()) {
                int pm = e.getKey(), pl = e.getValue();
                if ((pm & m) == 0) {
                    int nm = pm | m;
                    ndp.put(nm, Math.max(ndp.getOrDefault(nm, 0), pl + l));
                }
            }
            dp = ndp;
        }
        int ans = 0;
        for (int v : dp.values()) ans = Math.max(ans, v);
        return ans;
    }

    public boolean isCorrect(List<String> arr, int output) {
        return output == maxLength(arr);
    }
}