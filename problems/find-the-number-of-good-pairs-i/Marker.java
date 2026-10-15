import java.util.*;
class Marker {
  public int numberOfPairs(int[] nums1, int[] nums2, int k) {
    int ans = 0;
    for (final int num1 : nums1)
      for (final int num2 : nums2)
        if (num1 % (num2 * k) == 0)
          ++ans;
    return ans;
  }

    public boolean isCorrect(int[] nums1, int[] nums2, int k, int output) {
        int expected = numberOfPairs(nums1, nums2, k);
        return expected == output;
    }
}
