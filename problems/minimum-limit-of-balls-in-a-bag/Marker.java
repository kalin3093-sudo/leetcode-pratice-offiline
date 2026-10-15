import java.util.*;
class Marker {
  public int minimumSize(int[] nums, int maxOperations) {
    int l = 1;
    int r = Arrays.stream(nums).max().getAsInt();

    while (l < r) {
      final int m = (l + r) / 2;
      if (numOperations(nums, m) <= maxOperations)
        r = m;
      else
        l = m + 1;
    }

    return l;
  }

  // Returns the number of operations required to make m penalty.
  private int numOperations(int[] nums, int m) {
    int operations = 0;
    for (final int num : nums)
      operations += (num - 1) / m;
    return operations;
  }

    public boolean isCorrect(int[] nums, int maxOperations, int output) {
        int expected = minimumSize(nums, maxOperations);
        return expected == output;
    }
}
