import java.util.*;
class Marker {
  public int maxFrequencyElements(int[] nums) {
    final int MAX = 100;
    int ans = 0;
    int[] count = new int[MAX + 1];

    for (final int num : nums)
      ++count[num];

    final int maxFreq = Arrays.stream(count).max().getAsInt();

    for (final int freq : count)
      if (freq == maxFreq)
        ans += maxFreq;

    return ans;
  }

    public boolean isCorrect(int[] nums, int output) {
        int expected = maxFrequencyElements(nums);
        return expected == output;
    }
}
