import java.util.*;
class Marker {
  public int sumOfEncryptedInt(int[] nums) {
    int ans = 0;

    for (final int num : nums) {
      int maxDigit = 0;
      int base = 0;
      for (int x = num; x > 0; x /= 10) {
        maxDigit = Math.max(maxDigit, x % 10);
        base = base * 10 + 1;
      }
      ans += base * maxDigit;
    }

    return ans;
  }

    public boolean isCorrect(int[] nums, int output) {
        int expected = sumOfEncryptedInt(nums);
        return expected == output;
    }
}
