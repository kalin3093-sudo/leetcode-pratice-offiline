import java.util.*;
class Marker {
  public int sumIndicesWithKSetBits(List<Integer> nums, int k) {
    int ans = 0;
    for (int i = 0; i < nums.size(); ++i)
      if (Integer.bitCount(i) == k)
        ans += nums.get(i);
    return ans;
  }

    public boolean isCorrect(List<Integer> nums, int k, int output) {
        int expected = sumIndicesWithKSetBits(nums, k);
        return expected == output;
    }
}
