import java.util.*;

class Marker {
  public long sumOfAncestors(int n, int[][] edges, int[] nums) {
    List<List<Integer>> adj = new ArrayList<>();
    for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
    for (int[] e : edges) {
      adj.get(e[0]).add(e[1]);
      adj.get(e[1]).add(e[0]);
    }
    int[] kernel = new int[n];
    for (int i = 0; i < n; i++) kernel[i] = squareFree(nums[i]);
    int[] freq = new int[100001];
    long ans = 0;
    Deque<int[]> stack = new ArrayDeque<>();
    stack.push(new int[] {0, -1, 0});
    while (!stack.isEmpty()) {
      int[] cur = stack.pop();
      int u = cur[0];
      if (cur[2] == 1) {
        freq[kernel[u]]--;
        continue;
      }
      ans += freq[kernel[u]];
      freq[kernel[u]]++;
      stack.push(new int[] {u, cur[1], 1});
      for (int v : adj.get(u)) {
        if (v != cur[1]) stack.push(new int[] {v, u, 0});
      }
    }
    return ans;
  }

  public boolean isCorrect(int n, int[][] edges, int[] nums, long output) {
    return sumOfAncestors(n, edges, nums) == output;
  }

  private int squareFree(int x) {
    for (int p = 2; p * p <= x; p++) {
      while (x % (p * p) == 0) x /= p * p;
    }
    return x;
  }
}
