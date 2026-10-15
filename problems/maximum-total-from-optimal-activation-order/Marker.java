import java.util.*;
class Marker {
    public long maxTotal(int[] value, int[] limit) {
        int n = value.length;
        Map<Integer, List<Integer>> groups = new HashMap<>();
        for (int i = 0; i < n; i++) {
            groups.computeIfAbsent(limit[i], k -> new ArrayList<>()).add(value[i]);
        }
        
        long total = 0;
        for (Map.Entry<Integer, List<Integer>> entry : groups.entrySet()) {
            int lim = entry.getKey();
            List<Integer> vals = entry.getValue();
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for (int v : vals) {
                pq.offer(v);
                if (pq.size() > lim) pq.poll();
            }
            while (!pq.isEmpty()) total += pq.poll();
        }
        return total;
    }

    public boolean isCorrect(int[] value, int[] limit, long output) {
        long expected = maxTotal(value, limit);
        return expected == output;
    }
}