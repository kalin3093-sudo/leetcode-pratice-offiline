import java.util.*;

class Marker {
    public int maximumWidth(int[] planks) {
        Map<Long, Integer> freq = new HashMap<>();
        for (int p : planks) freq.merge((long) p, 1, Integer::sum);
        List<Long> heights = new ArrayList<>(freq.keySet());
        Map<Long, Integer> pairs = new HashMap<>();
        int d = heights.size();
        for (int i = 0; i < d; i++) {
            for (int j = i; j < d; j++) {
                long h = heights.get(i) + heights.get(j);
                int cnt;
                if (i == j) cnt = freq.get(heights.get(i)) / 2;
                else cnt = Math.min(freq.get(heights.get(i)), freq.get(heights.get(j)));
                pairs.merge(h, cnt, Integer::sum);
            }
        }
        int ans = 0;
        for (long h : heights) {
            ans = Math.max(ans, freq.get(h) + pairs.getOrDefault(h, 0));
        }
        for (Map.Entry<Long, Integer> e : pairs.entrySet()) {
            if (!freq.containsKey(e.getKey())) ans = Math.max(ans, e.getValue());
        }
        return ans;
    }

    public boolean isCorrect(int[] planks, int output) {
        return output == maximumWidth(planks);
    }
}
