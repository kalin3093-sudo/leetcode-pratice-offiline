import java.util.*;

class Marker {
    public int primeSubarray(int[] nums, int k) {
        int maxVal = 0;
        for (int x : nums) maxVal = Math.max(maxVal, x);
        boolean[] isPrime = sieve(maxVal);
        int[] primes = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            primes[i] = isPrime[nums[i]] ? nums[i] : 0;
        }

        int n = nums.length;
        int[] log = new int[n + 1];
        for (int i = 2; i <= n; i++) log[i] = log[i / 2] + 1;
        int K = log[n] + 1;

        int[][] stMin = new int[K][n];
        int[][] stMax = new int[K][n];
        for (int i = 0; i < n; i++) {
            stMin[0][i] = nums[i];
            stMax[0][i] = nums[i];
        }
        for (int j = 1; j < K; j++) {
            for (int i = 0; i + (1 << j) <= n; i++) {
                stMin[j][i] = Math.min(stMin[j - 1][i], stMin[j - 1][i + (1 << (j - 1))]);
                stMax[j][i] = Math.max(stMax[j - 1][i], stMax[j - 1][i + (1 << (j - 1))]);
            }
        }

        List<Integer> primeIndices = new ArrayList<>();
        for (int i = 0; i < n; i++) if (primes[i] > 0) primeIndices.add(i);

        int p = primeIndices.size();
        if (p < 2) return 0;

        int[] rightGap = new int[p];
        for (int j = 0; j < p; j++) {
            rightGap[j] = (j == p - 1) ? n - primeIndices.get(j) : primeIndices.get(j + 1) - primeIndices.get(j);
        }
        int[] rightGapPrefix = new int[p + 1];
        for (int j = 0; j < p; j++) {
            rightGapPrefix[j + 1] = rightGapPrefix[j] + rightGap[j];
        }

        long ans = 0;
        for (int idx = 0; idx < p - 1; idx++) {
            int i = primeIndices.get(idx);
            int lo = idx + 1, hi = p - 1, best = idx;
            while (lo <= hi) {
                int mid = (lo + hi) / 2;
                int j = primeIndices.get(mid);
                int qMin = queryMin(stMin, log, i, j);
                int qMax = queryMax(stMax, log, i, j);
                if (qMax - qMin <= k) {
                    best = mid;
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
            if (best > idx) {
                int leftGap = idx == 0 ? primeIndices.get(idx) + 1 : primeIndices.get(idx) - primeIndices.get(idx - 1);
                int sumRight = rightGapPrefix[best + 1] - rightGapPrefix[idx + 1];
                ans += 1L * leftGap * sumRight;
            }
        }
        return (int) ans;
    }

    private boolean[] sieve(int n) {
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        if (n >= 0) isPrime[0] = false;
        if (n >= 1) isPrime[1] = false;
        for (int p = 2; p * p <= n; p++) {
            if (isPrime[p]) {
                for (int m = p * p; m <= n; m += p) isPrime[m] = false;
            }
        }
        return isPrime;
    }

    private int queryMin(int[][] st, int[] log, int l, int r) {
        int j = log[r - l + 1];
        return Math.min(st[j][l], st[j][r - (1 << j) + 1]);
    }

    private int queryMax(int[][] st, int[] log, int l, int r) {
        int j = log[r - l + 1];
        return Math.max(st[j][l], st[j][r - (1 << j) + 1]);
    }

    public boolean isCorrect(int[] nums, int k, int output) {
        return output == primeSubarray(nums, k);
    }
}