import java.util.*;
class Marker {
  public int[] decompressRLElist(int[] nums) {
    List<Integer> ans = new ArrayList<>();

    for (int i = 0; i < nums.length; i += 2)
      for (int freq = 0; freq < nums[i]; ++freq)
        ans.add(nums[i + 1]);

    return ans.stream().mapToInt(Integer::intValue).toArray();
  }

    public boolean isCorrect(int[] nums, int[] output) {
        int[] expected = decompressRLElist(nums);
        return Arrays.equals(expected, output);
    }
}
