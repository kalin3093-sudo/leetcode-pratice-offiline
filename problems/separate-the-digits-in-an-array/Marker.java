import java.util.*;
class Marker {
  public int[] separateDigits(int[] nums) {
    List<Integer> ans = new ArrayList<>();

    for (final int num : nums)
      for (final char c : String.valueOf(num).toCharArray())
        ans.add(c - '0');

    return ans.stream().mapToInt(Integer::intValue).toArray();
  }

    public boolean isCorrect(int[] nums, int[] output) {
        int[] expected = separateDigits(nums);
        return Arrays.equals(expected, output);
    }
}
