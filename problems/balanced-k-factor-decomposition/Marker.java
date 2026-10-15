import java.util.*;
class Marker {
    public int[] minDifference(int n, int k) {
        List<Integer> divs = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                divs.add(i);
                if (i != n / i) divs.add(n / i);
            }
        }
        Collections.sort(divs);
        int[] path = new int[k];
        int[] best = new int[k];
        int[] bestDiff = {Integer.MAX_VALUE};
        dfs(0, 1, 0, n, divs, path, best, bestDiff);
        return best;
    }

    private static void dfs(int idx, long prod, int start, int n, List<Integer> divs, int[] path, int[] best, int[] bestDiff) {
        if (idx == path.length) {
            if (prod == n) {
                int diff = path[path.length - 1] - path[0];
                if (diff < bestDiff[0]) {
                    bestDiff[0] = diff;
                    System.arraycopy(path, 0, best, 0, path.length);
                }
            }
            return;
        }
        for (int i = start; i < divs.size(); i++) {
            int d = divs.get(i);
            if (prod * d > n) break;
            path[idx] = d;
            dfs(idx + 1, prod * d, i, n, divs, path, best, bestDiff);
        }
    }

    public boolean isCorrect(int n, int k, int[] output) {
        if (output == null || output.length != k) return false;
        long prod = 1;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int v : output) {
            if (v < 1) return false;
            if (prod > n / v) return false;
            prod *= v;
            min = Math.min(min, v);
            max = Math.max(max, v);
        }
        if (prod != n) return false;
        int[] ref = minDifference(n, k);
        return max - min == ref[ref.length - 1] - ref[0];
    }
}
