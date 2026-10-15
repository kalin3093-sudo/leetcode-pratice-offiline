import java.util.*;
class Marker {
  public long maximumTripletValue(int[] nums) {
    long ans = 0;
    int maxDiff = 0; // max(nums[i] - nums[j])
    int maxNum = 0;  // max(nums[i])

    for (final int num : nums) {
      ans = Math.max(ans, (long) maxDiff * num); // num := nums[k]
      maxDiff = Math.max(maxDiff, maxNum - num); // num := nums[j]
      maxNum = Math.max(maxNum, num);            // num := nums[i]
    }

    return ans;
  }

    public boolean isCorrect(int[] nums, long output) {
        long expected = maximumTripletValue(nums);
        return expected == output;
    }
}
