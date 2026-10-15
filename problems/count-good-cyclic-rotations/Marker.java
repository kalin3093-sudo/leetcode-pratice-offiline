public class Marker {

    public int countGoodRotations(int[] nums) {
        int n = nums.length, h = n / 2;
        long[] pref = new long[2 * n + 1];
        for (int i = 0; i < 2 * n; i++) pref[i + 1] = pref[i] + nums[i % n];
        int cnt = 0;
        for (int p = 0; p < n; p++) {
            long first = pref[p + h] - pref[p];
            long second = pref[p + n] - pref[p + h];
            if (first > second) cnt++;
        }
        return cnt;
    }

    public boolean isCorrect(int[] nums, int out) {
        return countGoodRotations(nums) == out;
    }
}
