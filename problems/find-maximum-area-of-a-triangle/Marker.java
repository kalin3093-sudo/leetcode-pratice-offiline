import java.util.*;
class Marker {
    public long maxArea(int[][] coords) {
        Map<Integer, List<Integer>> byX = new HashMap<>();
        Map<Integer, List<Integer>> byY = new HashMap<>();
        for (int[] p : coords) {
            byX.computeIfAbsent(p[0], k -> new ArrayList<>()).add(p[1]);
            byY.computeIfAbsent(p[1], k -> new ArrayList<>()).add(p[0]);
        }
        long best2 = 0;
        
        int[] minYArr = new int[101];
        int[] maxYArr = new int[101];
        Arrays.fill(minYArr, 101);
        Arrays.fill(maxYArr, -1);
        for (int[] p : coords) {
            minYArr[p[0]] = Math.min(minYArr[p[0]], p[1]);
            maxYArr[p[0]] = Math.max(maxYArr[p[0]], p[1]);
        }
        
        int[] minXArr = new int[101];
        int[] maxXArr = new int[101];
        Arrays.fill(minXArr, 101);
        Arrays.fill(maxXArr, -1);
        for (int[] p : coords) {
            minXArr[p[1]] = Math.min(minXArr[p[1]], p[0]);
            maxXArr[p[1]] = Math.max(maxXArr[p[1]], p[0]);
        }
        
        for (int y = 0; y <= 100; y++) {
            if (maxXArr[y] == -1) continue;
            List<Integer> xs = byY.get(y);
            if (xs.size() < 2) continue;
            int base = maxXArr[y] - minXArr[y];
            int maxH = 0;
            for (int y2 = 0; y2 <= 100; y2++) {
                if (maxXArr[y2] == -1) continue;
                maxH = Math.max(maxH, Math.abs(y2 - y));
            }
            best2 = Math.max(best2, (long) base * maxH);
        }
        
        for (int x = 0; x <= 100; x++) {
            if (maxYArr[x] == -1) continue;
            List<Integer> ys = byX.get(x);
            if (ys.size() < 2) continue;
            int base = maxYArr[x] - minYArr[x];
            int maxH = 0;
            for (int x2 = 0; x2 <= 100; x2++) {
                if (maxYArr[x2] == -1) continue;
                maxH = Math.max(maxH, Math.abs(x2 - x));
            }
            best2 = Math.max(best2, (long) base * maxH);
        }
        
        return best2 == 0 ? -1 : best2;
    }

    public boolean isCorrect(int[][] coords, long output) {
        return maxArea(coords) == output;
    }
}