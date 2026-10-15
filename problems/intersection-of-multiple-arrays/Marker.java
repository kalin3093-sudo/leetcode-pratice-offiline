import java.util.*;
class Marker {
  public List<Integer> intersection(int[][] nums) {
    final int MAX = 1000;
    List<Integer> ans = new ArrayList<>();
    int[] count = new int[MAX + 1];

    for (int[] arr : nums)
      for (final int a : arr)
        ++count[a];

    for (int i = 1; i <= MAX; ++i)
      if (count[i] == nums.length)
        ans.add(i);

    return ans;
  }

    public boolean isCorrect(int[][] nums, List<Integer> output) {
        List<Integer> expected = intersection(nums);
        return expected.equals(output);
    }
}
