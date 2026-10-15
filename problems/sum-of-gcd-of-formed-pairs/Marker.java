import java.util.*;

class Marker {
  public long gcdSum(int[] nums) {
    int mx = 0;
    int[] g = new int[nums.length];
    for (int i = 0; i < nums.length; i++) {
      mx = Math.max(mx, nums[i]);
      g[i] = gcd(nums[i], mx);
    }
    Arrays.sort(g);
    long ans = 0;
    for (int i = 0, j = g.length - 1; i < j; i++, j--) {
      ans += gcd(g[i], g[j]);
    }
    return ans;
  }

  public boolean isCorrect(int[] nums, long output) {
    return gcdSum(nums) == output;
  }

  private int gcd(int a, int b) {
    while (b != 0) {
      int t = a % b;
      a = b;
      b = t;
    }
    return a;
  }
}
