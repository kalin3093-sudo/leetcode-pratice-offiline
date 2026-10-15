import java.util.*;
class Marker {
    public int[] minCost(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] closest = new int[n];
        for (int i = 0; i < n; i++) {
            if (i == 0) closest[i] = 1;
            else if (i == n - 1) closest[i] = n - 2;
            else {
                int diffLeft = nums[i] - nums[i - 1];
                int diffRight = nums[i + 1] - nums[i];
                if (diffLeft <= diffRight) closest[i] = i - 1;
                else closest[i] = i + 1;
            }
        }

        // Find basins and 2-cycles
        int[] state = new int[n]; // 0=unvisited, 1=visiting, 2=done
        int[] cycleLeft = new int[n];
        int[] cycleRight = new int[n];
        int[] direction = new int[n]; // +1=right, -1=left, 0=cycle

        for (int i = 0; i < n; i++) {
            if (state[i] != 0) continue;
            int u = i;
            List<Integer> path = new ArrayList<>();
            while (state[u] == 0) {
                state[u] = 1;
                path.add(u);
                u = closest[u];
            }
            if (state[u] == 1) {
                // Found cycle
                int cycleStart = path.indexOf(u);
                List<Integer> cycle = path.subList(cycleStart, path.size());
                // Should be 2-cycle
                int lc = cycle.get(0), rc = cycle.get(1);
                if (lc > rc) { int tmp = lc; lc = rc; rc = tmp; }
                for (int v : cycle) {
                    cycleLeft[v] = lc;
                    cycleRight[v] = rc;
                    direction[v] = 0;
                    state[v] = 2;
                }
                // Process path before cycle
                for (int j = cycleStart - 1; j >= 0; j--) {
                    int v = path.get(j);
                    cycleLeft[v] = lc;
                    cycleRight[v] = rc;
                    direction[v] = (closest[v] == v + 1) ? 1 : -1;
                    state[v] = 2;
                }
            } else { // state[u] == 2
                // u already processed, inherit its basin
                int lc = cycleLeft[u];
                int rc = cycleRight[u];
                for (int v : path) {
                    cycleLeft[v] = lc;
                    cycleRight[v] = rc;
                    direction[v] = (closest[v] == v + 1) ? 1 : -1;
                    state[v] = 2;
                }
            }
        }

        // Precompute A = i - nums[i], B = i + nums[i]
        int[] A = new int[n];
        int[] B = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = i - nums[i];
            B[i] = i + nums[i];
        }

        // Sparse tables for range min/max of A and B
        int LOG = 17; // ceil(log2(100000)) = 17
        int[][] minA = new int[LOG][n];
        int[][] maxA = new int[LOG][n];
        int[][] minB = new int[LOG][n];
        int[][] maxB = new int[LOG][n];
        for (int i = 0; i < n; i++) {
            minA[0][i] = A[i];
            maxA[0][i] = A[i];
            minB[0][i] = B[i];
            maxB[0][i] = B[i];
        }
        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i + (1 << k) <= n; i++) {
                minA[k][i] = Math.min(minA[k-1][i], minA[k-1][i + (1 << (k-1))]);
                maxA[k][i] = Math.max(maxA[k-1][i], maxA[k-1][i + (1 << (k-1))]);
                minB[k][i] = Math.min(minB[k-1][i], minB[k-1][i + (1 << (k-1))]);
                maxB[k][i] = Math.max(maxB[k-1][i], maxB[k-1][i + (1 << (k-1))]);
            }
        }
        int[] log2 = new int[n + 1];
        for (int i = 2; i <= n; i++) log2[i] = log2[i/2] + 1;

        int q = queries.length;
        int[] ans = new int[q];
        for (int qi = 0; qi < q; qi++) {
            int L = queries[qi][0];
            int R = queries[qi][1];
            long direct = Math.abs((long)nums[L] - nums[R]);
            long best = direct;

            int lc = cycleLeft[L];
            int rc = cycleRight[L];
            int dir = direction[L];

            if (dir == 1) { // flowing right, interval [L, rc]
                if (R >= rc) {
                    int minVal = queryMin(minA, log2, L, rc);
                    best = Math.min(best, nums[R] - L + minVal);
                } else if (R < L) {
                    int minVal = queryMin(minB, log2, L, rc);
                    best = Math.min(best, minVal - L - nums[R]);
                } else { // L <= R < rc
                    int minVal1 = queryMin(minA, log2, L, R);
                    best = Math.min(best, nums[R] - L + minVal1);
                    if (R + 1 <= rc) {
                        int minVal2 = queryMin(minB, log2, R + 1, rc);
                        best = Math.min(best, minVal2 - L - nums[R]);
                    }
                }
            } else if (dir == -1) { // flowing left, interval [lc, L]
                if (R >= L) {
                    int maxVal = queryMax(maxB, log2, lc, L);
                    best = Math.min(best, L + nums[R] - maxVal);
                } else if (R < lc) {
                    int maxVal = queryMax(maxA, log2, lc, L);
                    best = Math.min(best, L - nums[R] - maxVal);
                } else { // lc <= R < L
                    int maxVal1 = queryMax(maxB, log2, lc, R);
                    best = Math.min(best, L + nums[R] - maxVal1);
                    int maxVal2 = queryMax(maxA, log2, R + 1, L);
                    best = Math.min(best, L - nums[R] - maxVal2);
                }
            } else { // in 2-cycle, interval [lc, rc]
                // lc and rc are the two nodes
                long cost1 = (L == lc ? 0 : 1) + Math.abs((long)nums[R] - nums[lc]);
                long cost2 = (L == rc ? 0 : 1) + Math.abs((long)nums[R] - nums[rc]);
                best = Math.min(best, Math.min(cost1, cost2));
            }

            ans[qi] = (int)best;
        }
        return ans;
    }

    private int queryMin(int[][] st, int[] log2, int l, int r) {
        int k = log2[r - l + 1];
        return Math.min(st[k][l], st[k][r - (1 << k) + 1]);
    }

    private int queryMax(int[][] st, int[] log2, int l, int r) {
        int k = log2[r - l + 1];
        return Math.max(st[k][l], st[k][r - (1 << k) + 1]);
    }

    public boolean isCorrect(int[] nums, int[][] queries, int[] output) {
        int[] expected = minCost(nums, queries);
        return Arrays.equals(expected, output);
    }
}