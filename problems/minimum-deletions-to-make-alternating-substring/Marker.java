import java.util.*;
class Marker {
    public int[] minDeletions(String s, int[][] queries) {
        int n = s.length();
        char[] arr = s.toCharArray();
        int[] eq = new int[n]; // eq[0] unused (or 0)
        for (int i = 1; i < n; i++) {
            eq[i] = (arr[i] == arr[i-1]) ? 1 : 0;
        }
        Fenwick bit = new Fenwick(n);
        for (int i = 1; i < n; i++) {
            if (eq[i] == 1) bit.add(i, 1);
        }

        List<Integer> ans = new ArrayList<>();
        for (int[] q : queries) {
            if (q[0] == 1) {
                int j = q[1];
                // flip arr[j]
                arr[j] = (arr[j] == 'A') ? 'B' : 'A';
                // update eq[j]
                if (j >= 1) {
                    int newEq = (arr[j] == arr[j-1]) ? 1 : 0;
                    if (newEq != eq[j]) {
                        bit.add(j, newEq - eq[j]);
                        eq[j] = newEq;
                    }
                }
                // update eq[j+1]
                if (j + 1 < n) {
                    int newEq = (arr[j+1] == arr[j]) ? 1 : 0;
                    if (newEq != eq[j+1]) {
                        bit.add(j+1, newEq - eq[j+1]);
                        eq[j+1] = newEq;
                    }
                }
            } else {
                int l = q[1], r = q[2];
                if (l == r) {
                    ans.add(0);
                } else {
                    int res = bit.sum(r) - bit.sum(l);
                    ans.add(res);
                }
            }
        }
        return ans.stream().mapToInt(i -> i).toArray();
    }

    static class Fenwick {
        int n;
        int[] bit;
        Fenwick(int n) {
            this.n = n;
            bit = new int[n + 1];
        }
        void add(int idx, int delta) {
            for (int i = idx + 1; i <= n; i += i & -i) bit[i] += delta;
        }
        int sum(int idx) { // sum [0..idx]
            int res = 0;
            for (int i = idx + 1; i > 0; i -= i & -i) res += bit[i];
            return res;
        }
    }

    public boolean isCorrect(String s, int[][] queries, int[] output) {
        int[] expected = minDeletions(s, queries);
        return Arrays.equals(expected, output);
    }
}