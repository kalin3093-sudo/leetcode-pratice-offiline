import java.util.*;
class Marker {
  public int[] createTargetArray(int[] nums, int[] index) {
    List<Integer> ans = new ArrayList<>();
    for (int i = 0; i < nums.length; i++)
      ans.add(index[i], nums[i]);
    return ans.stream().mapToInt(Integer::intValue).toArray();
  }

    public boolean isCorrect(int[] nums, int[] index, int[] output) {
        int[] expected = createTargetArray(nums, index);
        return Arrays.equals(expected, output);
    }
}
