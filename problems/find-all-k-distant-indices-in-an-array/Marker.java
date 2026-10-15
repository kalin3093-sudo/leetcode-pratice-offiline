import java.util.*;
class Marker {
  public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
    final int n = nums.length;
    List<Integer> ans = new ArrayList<>();

    for (int i = 0, j = 0; i < n; ++i) {
      // the first index j s.t. nums[j] == key and j >= i - k
      while (j < n && (nums[j] != key || j < i - k))
        ++j;
      if (j == n)
        break;
      if (Math.abs(i - j) <= k)
        ans.add(i);
    }

    return ans;
  }

    public boolean isCorrect(int[] nums, int key, int k, List<Integer> output) {
        List<Integer> expected = findKDistantIndices(nums, key, k);
        return expected.equals(output);
    }
}
