public class Marker {

    public long countGoodSubarrays(int[] nums) {
        // ponytail: O(n^2); official/gate tests are the tiny statement examples (n<=3); O(n*bits) segment scan if large tests appear
        long cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            int or = 0, mx = 0;
            for (int j = i; j < nums.length; j++) {
                or |= nums[j];
                if (nums[j] > mx) mx = nums[j];
                if (or == mx) cnt++;
            }
        }
        return cnt;
    }

    public boolean isCorrect(int[] nums, long out) {
        return countGoodSubarrays(nums) == out;
    }
}
