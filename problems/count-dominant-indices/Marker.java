public class Marker {

    public int dominantIndices(int[] nums) {
        int n = nums.length;
        long suffix = 0;
        int count = 0;
        for (int i = n - 1; i >= 0; i--) {
            int cnt = n - 1 - i;
            if (cnt > 0 && (long) nums[i] * cnt > suffix) count++;
            suffix += nums[i];
        }
        return count;
    }

    public boolean isCorrect(int[] nums, int out) {
        return dominantIndices(nums) == out;
    }
}
