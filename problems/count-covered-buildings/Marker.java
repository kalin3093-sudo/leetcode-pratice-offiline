import java.util.*;

public class Marker {

    public int countCoveredBuildings(int n, int[][] buildings) {
        Map<Integer, List<Integer>> byRow = new HashMap<>();
        Map<Integer, List<Integer>> byCol = new HashMap<>();
        for (int[] b : buildings) {
            byRow.computeIfAbsent(b[1], k -> new ArrayList<>()).add(b[0]);
            byCol.computeIfAbsent(b[0], k -> new ArrayList<>()).add(b[1]);
        }
        Set<Long> rowInt = new HashSet<>();
        for (Map.Entry<Integer, List<Integer>> e : byRow.entrySet()) {
            List<Integer> xs = e.getValue();
            Collections.sort(xs);
            for (int i = 1; i < xs.size() - 1; i++) rowInt.add(key(xs.get(i), e.getKey()));
        }
        Set<Long> colInt = new HashSet<>();
        for (Map.Entry<Integer, List<Integer>> e : byCol.entrySet()) {
            List<Integer> ys = e.getValue();
            Collections.sort(ys);
            for (int i = 1; i < ys.size() - 1; i++) colInt.add(key(e.getKey(), ys.get(i)));
        }
        int ans = 0;
        for (int[] b : buildings) {
            long k = key(b[0], b[1]);
            if (rowInt.contains(k) && colInt.contains(k)) ans++;
        }
        return ans;
    }

    long key(int x, int y) {
        return ((long) x << 32) | (y & 0xFFFFFFFFL);
    }

    public boolean isCorrect(int n, int[][] buildings, int out) {
        return countCoveredBuildings(n, buildings) == out;
    }
}
