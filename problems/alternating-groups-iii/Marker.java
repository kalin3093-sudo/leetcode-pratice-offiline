import java.util.*;
class Marker {
    private static void bitAdd(long[] bit, int i, long v) {
        for (; i < bit.length; i += i & -i) bit[i] += v;
    }

    private static long bitSum(long[] bit, int i) {
        long s = 0;
        for (; i > 0; i -= i & -i) s += bit[i];
        return s;
    }

    public List<Integer> numberOfAlternatingGroups(int[] colors, int[][] queries) {
        colors = colors.clone();
        int n = colors.length;
        boolean[] d = new boolean[n];
        for (int i = 0; i < n; i++) d[i] = colors[i] != colors[(i + 1) % n];
        TreeSet<Integer> zeros = new TreeSet<>();
        long[] cntB = new long[n + 2], sumB = new long[n + 2], tot = new long[2];

        for (int i = 0; i < n; i++) if (!d[i]) zeros.add(i);
        if (!zeros.isEmpty()) {
            Integer prev = null, first = null;
            for (int z : zeros) { if (first == null) first = z; if (prev != null) addRun(cntB, sumB, tot, (z - prev - 1 + n) % n); prev = z; }
            addRun(cntB, sumB, tot, (first - prev - 1 + n) % n);
        }

        List<Integer> res = new ArrayList<>();
        for (int[] q : queries) {
            if (q[0] == 1) {
                int w = q[1] - 1;
                if (zeros.isEmpty()) {
                    res.add(n);
                } else {
                    long cnt = tot[0] - bitSum(cntB, w - 1);
                    long sum = tot[1] - bitSum(sumB, w - 1);
                    res.add((int) (sum - (long) (w - 1) * cnt));
                }
            } else {
                int idx = q[1], color = q[2];
                if (colors[idx] == color) continue;
                colors[idx] = color;
                int nx = (idx + 1) % n, pv = (idx - 1 + n) % n;
                boolean nd = colors[idx] != colors[nx];
                if (nd != d[idx]) {
                    d[idx] = nd;
                    if (nd) delZero(idx, zeros, n, cntB, sumB, tot);
                    else insZero(idx, zeros, n, cntB, sumB, tot);
                }
                boolean ndp = colors[pv] != colors[idx];
                if (ndp != d[pv]) {
                    d[pv] = ndp;
                    if (ndp) delZero(pv, zeros, n, cntB, sumB, tot);
                    else insZero(pv, zeros, n, cntB, sumB, tot);
                }
            }
        }
        return res;
    }

    private static void addRun(long[] cntB, long[] sumB, long[] tot, int len) {
        if (len <= 0) return;
        bitAdd(cntB, len, 1);
        bitAdd(sumB, len, len);
        tot[0]++;
        tot[1] += len;
    }

    private static void remRun(long[] cntB, long[] sumB, long[] tot, int len) {
        if (len <= 0) return;
        bitAdd(cntB, len, -1);
        bitAdd(sumB, len, -len);
        tot[0]--;
        tot[1] -= len;
    }

    private static void insZero(int z, TreeSet<Integer> zeros, int n, long[] cntB, long[] sumB, long[] tot) {
        if (zeros.isEmpty()) {
            addRun(cntB, sumB, tot, n - 1);
            zeros.add(z);
            return;
        }
        Integer a = zeros.floor(z), b = zeros.ceiling(z);
        if (a == null) a = zeros.last();
        if (b == null) b = zeros.first();
        int l1 = (z - a - 1 + n) % n, l2 = (b - z - 1 + n) % n, l = (b - a - 1 + n) % n;
        remRun(cntB, sumB, tot, l);
        addRun(cntB, sumB, tot, l1);
        addRun(cntB, sumB, tot, l2);
        zeros.add(z);
    }

    private static void delZero(int z, TreeSet<Integer> zeros, int n, long[] cntB, long[] sumB, long[] tot) {
        if (zeros.size() == 1) {
            remRun(cntB, sumB, tot, n - 1);
            zeros.remove(z);
            return;
        }
        Integer a = zeros.lower(z), b = zeros.higher(z);
        if (a == null) a = zeros.last();
        if (b == null) b = zeros.first();
        int l1 = (z - a - 1 + n) % n, l2 = (b - z - 1 + n) % n;
        remRun(cntB, sumB, tot, l1);
        remRun(cntB, sumB, tot, l2);
        addRun(cntB, sumB, tot, l1 + l2 + 1);
        zeros.remove(z);
    }

    public boolean isCorrect(int[] colors, int[][] queries, List<Integer> output) {
        if (output == null) return false;
        return numberOfAlternatingGroups(colors, queries).equals(output);
    }
}
