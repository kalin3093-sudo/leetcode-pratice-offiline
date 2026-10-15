import java.util.*;
class Marker {
    private static int primeCount(int x) {
        int cnt = 0;
        for (int p = 2; p * p <= x; p++) {
            if (x % p == 0) {
                cnt++;
                while (x % p == 0) x /= p;
            }
        }
        if (x > 1) cnt++;
        return cnt;
    }

    public int maximumScore(List<Integer> nums, int k) {
        int n = nums.size();
        int[] score = new int[n];
        for (int i = 0; i < n; i++) score[i] = primeCount(nums.get(i));
        int[] left = new int[n], right = new int[n];
        Deque<Integer> st = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && score[st.peek()] < score[i]) st.pop();
            left[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }
        st.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && score[st.peek()] <= score[i]) st.pop();
            right[i] = st.isEmpty() ? n : st.peek();
            st.push(i);
        }
        long[] ranges = new long[n];
        for (int i = 0; i < n; i++) ranges[i] = (long) (i - left[i]) * (right[i] - i);
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) order[i] = i;
        Arrays.sort(order, (x, y) -> Integer.compare(nums.get(y), nums.get(x)));
        long ans = 1;
        long MOD = 1_000_000_007L;
        long rem = k;
        for (int i : order) {
            if (rem <= 0) break;
            long take = Math.min(ranges[i], rem);
            ans = ans * modPow(nums.get(i), take, MOD) % MOD;
            rem -= take;
        }
        return (int) ans;
    }

    private static long modPow(long b, long e, long mod) {
        long r = 1;
        b %= mod;
        while (e > 0) {
            if ((e & 1) == 1) r = r * b % mod;
            b = b * b % mod;
            e >>= 1;
        }
        return r;
    }

    public boolean isCorrect(List<Integer> nums, int k, int output) {
        return maximumScore(nums, k) == output;
    }
}
