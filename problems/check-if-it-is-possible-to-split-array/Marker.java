import java.util.*;
class Marker {
  public boolean canSplitArray(List<Integer> nums, int m) {
    if (nums.size() < 3)
      return true;

    for (int i = 1; i < nums.size(); ++i)
      if (nums.get(i) + nums.get(i - 1) >= m)
        return true;

    return false;
  }

    public boolean isCorrect(List<Integer> nums, int m, boolean output) {
        boolean expected = canSplitArray(nums, m);
        return expected == output;
    }
}
