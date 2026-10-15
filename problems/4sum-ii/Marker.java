import java.util.*;
class Marker {
  public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
    int ans = 0;
    Map<Integer, Integer> count = new HashMap<>();

    for (final int a : nums1)
      for (final int b : nums2)
        count.merge(a + b, 1, Integer::sum);

    for (final int c : nums3)
      for (final int d : nums4)
        if (count.containsKey(-c - d))
          ans += count.get(-c - d);

    return ans;
  }

    public boolean isCorrect(int[] nums1, int[] nums2, int[] nums3, int[] nums4, int output) {
        int expected = fourSumCount(nums1, nums2, nums3, nums4);
        return expected == output;
    }
}
