import java.util.*;
class Marker {
  public int partitionArray(int[] nums, int k) {
    Arrays.sort(nums);

    int ans = 1;
    int mn = nums[0];

    for (int i = 1; i < nums.length; ++i)
      if (mn + k < nums[i]) {
        ++ans;
        mn = nums[i];
      }

    return ans;
  }

    public boolean isCorrect(int[] nums, int k, int output) {
        int expected = partitionArray(nums, k);
        return expected == output;
    }
}
