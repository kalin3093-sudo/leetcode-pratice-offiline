import java.util.*;
class Marker {
  public int mostFrequent(int[] nums, int key) {
    int[] count = new int[1001];
    int ans = 0;

    for (int i = 0; i + 1 < nums.length; ++i)
      if (nums[i] == key)
        ++count[nums[i + 1]];

    for (int i = 1; i < 1001; ++i)
      if (count[i] > count[ans])
        ans = i;

    return ans;
  }

    public boolean isCorrect(int[] nums, int key, int output) {
        int expected = mostFrequent(nums, key);
        return expected == output;
    }
}
