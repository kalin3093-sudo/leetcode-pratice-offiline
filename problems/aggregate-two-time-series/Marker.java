import java.util.*;
class Marker {
    private static int lookup(int[][] s, int t) {
        int lo = 0, hi = s.length, ans = -1;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (s[mid][0] >= t) { ans = mid; hi = mid; } else lo = mid + 1;
        }
        return ans == -1 ? 0 : s[ans][1];
    }

    public List<List<Integer>> aggregateTimeSeries(int[][] series1, int[][] series2) {
        int[][] a = series1.clone(), b = series2.clone();
        Arrays.sort(a, (x, y) -> Integer.compare(x[0], y[0]));
        Arrays.sort(b, (x, y) -> Integer.compare(x[0], y[0]));
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int[] p : a) map.put(p[0], 0);
        for (int[] p : b) map.put(p[0], 0);
        List<List<Integer>> res = new ArrayList<>();
        for (int t : map.keySet()) {
            res.add(Arrays.asList(t, lookup(a, t) + lookup(b, t)));
        }
        return res;
    }

    public boolean isCorrect(int[][] series1, int[][] series2, List<List<Integer>> output) {
        if (output == null) return false;
        return aggregateTimeSeries(series1, series2).equals(output);
    }
}
