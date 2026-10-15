import java.util.*;
class Marker {
    public long countStableSubarrays(int[] capacity) {
        int n = capacity.length;
        long[] psum = new long[n + 1];
        for (int i = 0; i < n; i++) psum[i + 1] = psum[i] + capacity[i];
        Map<Integer, Map<Long, Long>> cnt = new HashMap<>();
        long ans = 0;
        for (int r = 2; r < n; r++) {
            int l = r - 2;
            cnt.computeIfAbsent(capacity[l], key -> new HashMap<>())
               .merge(psum[l + 1], 1L, Long::sum);
            long need = psum[r] - capacity[r];
            Map<Long, Long> inner = cnt.get(capacity[r]);
            if (inner != null) ans += inner.getOrDefault(need, 0L);
        }
        return ans;
    }
    public boolean isCorrect(int[] capacity, long output) {
        return output == countStableSubarrays(capacity);
    }
}
