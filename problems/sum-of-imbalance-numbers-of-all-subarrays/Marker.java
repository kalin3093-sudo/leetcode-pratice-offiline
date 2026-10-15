import java.util.*;

class Marker {
  public int sumImbalanceNumbers(int[] nums) {
    int n = nums.length;
    int total = 0;
    for (int l = 0; l < n; l++) {
      Set<Integer> set = new HashSet<>();
      set.add(nums[l]);
      int imbalance = 0;
      for (int r = l + 1; r < n; r++) {
        int x = nums[r];
        if (!set.contains(x)) {
          boolean prev = set.contains(x - 1);
          boolean next = set.contains(x + 1);
          if (!prev && !next) imbalance++;
          else if (prev && next) imbalance--;
          set.add(x);
        }
        total += imbalance;
      }
    }
    return total;
  }

  public boolean isCorrect(int[] nums, int output) {
    return sumImbalanceNumbers(nums) == output;
  }
}
