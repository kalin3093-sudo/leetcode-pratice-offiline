import java.util.*;
class Marker {
  public int[] rearrangeArray(int[] nums) {
    int[] ans = new int[nums.length];
    List<Integer> pos = new ArrayList<>();
    List<Integer> neg = new ArrayList<>();

    for (final int num : nums)
      (num > 0 ? pos : neg).add(num);

    for (int i = 0; i < pos.size(); ++i) {
      ans[i * 2] = pos.get(i);
      ans[i * 2 + 1] = neg.get(i);
    }

    return ans;
  }

    public boolean isCorrect(int[] nums, int[] output) {
        int[] expected = rearrangeArray(nums);
        return Arrays.equals(expected, output);
    }
}
