import java.util.*;
class Marker {
  public int longestEqualSubarray(List<Integer> nums, int k) {
    int ans = 0;
    Map<Integer, Integer> count = new HashMap<>();

    for (int l = 0, r = 0; r < nums.size(); ++r) {
      ans = Math.max(ans, count.merge(nums.get(r), 1, Integer::sum));
      while (r - l + 1 - k > ans)
        count.merge(nums.get(l++), -1, Integer::sum);
    }

    return ans;
  }

    public boolean isCorrect(List<Integer> nums, int k, int output) {
        int expected = longestEqualSubarray(nums, k);
        return expected == output;
    }
}
