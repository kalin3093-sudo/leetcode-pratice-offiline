import java.util.*;
class Marker {
  public int distributeCookies(int[] cookies, int k) {
    dfs(cookies, 0, k, new int[k]);
    return ans;
  }

  private int ans = Integer.MAX_VALUE;

  private void dfs(int[] cookies, int s, int k, int[] children) {
    if (s == cookies.length) {
      ans = Math.min(ans, Arrays.stream(children).max().getAsInt());
      return;
    }

    for (int i = 0; i < k; ++i) {
      children[i] += cookies[s];
      dfs(cookies, s + 1, k, children);
      children[i] -= cookies[s];
    }
  }

    public boolean isCorrect(int[] cookies, int k, int output) {
        int expected = distributeCookies(cookies, k);
        return expected == output;
    }
}
