import java.util.*;

class Marker {
  public int[] subarrayMajority(int[] nums, int[][] queries) {
    int n = nums.length;
    int q = queries.length;
    int[] sorted = nums.clone();
    Arrays.sort(sorted);
    int[] values = new int[n];
    int m = 0;
    for (int i = 0; i < n; i++) {
      if (i == 0 || sorted[i] != sorted[i - 1]) values[m++] = sorted[i];
    }
    int[] idx = new int[n];
    for (int i = 0; i < n; i++) idx[i] = Arrays.binarySearch(values, 0, m, nums[i]);

    int block = Math.max(1, (int) Math.sqrt(n));
    Integer[] order = new Integer[q];
    for (int i = 0; i < q; i++) order[i] = i;
    Arrays.sort(order, (x, y) -> {
      int bx = queries[x][0] / block;
      int by = queries[y][0] / block;
      if (bx != by) return bx - by;
      if ((bx & 1) == 0) return queries[x][1] - queries[y][1];
      return queries[y][1] - queries[x][1];
    });

    int[] cnt = new int[m];
    TreeSet<Integer>[] buckets = new TreeSet[n + 1];
    for (int i = 0; i <= n; i++) buckets[i] = new TreeSet<>();
    int[] state = new int[] {0, -1, 0};
    int[] ans = new int[q];

    for (int qi : order) {
      int l = queries[qi][0];
      int r = queries[qi][1];
      int thr = queries[qi][2];
      while (state[1] < r) add(idx, cnt, buckets, state, ++state[1]);
      while (state[0] > l) add(idx, cnt, buckets, state, --state[0]);
      while (state[1] > r) remove(idx, cnt, buckets, state, state[1]--);
      while (state[0] < l) remove(idx, cnt, buckets, state, state[0]++);

      int maxFreq = state[2];
      if (maxFreq < thr) ans[qi] = -1;
      else ans[qi] = values[buckets[maxFreq].first()];
    }
    return ans;
  }

  private void add(int[] idx, int[] cnt, TreeSet<Integer>[] buckets, int[] state, int pos) {
    int v = idx[pos];
    buckets[cnt[v]].remove(v);
    cnt[v]++;
    buckets[cnt[v]].add(v);
    if (cnt[v] > state[2]) state[2] = cnt[v];
  }

  private void remove(int[] idx, int[] cnt, TreeSet<Integer>[] buckets, int[] state, int pos) {
    int v = idx[pos];
    buckets[cnt[v]].remove(v);
    cnt[v]--;
    if (cnt[v] > 0) buckets[cnt[v]].add(v);
    if (cnt[v] + 1 == state[2] && buckets[state[2]].isEmpty()) {
      while (state[2] > 0 && buckets[state[2]].isEmpty()) state[2]--;
    }
  }

  public boolean isCorrect(int[] nums, int[][] queries, int[] output) {
    int[] expected = subarrayMajority(nums, queries);
    if (output == null || output.length != expected.length) return false;
    for (int i = 0; i < expected.length; i++) {
      if (output[i] != expected[i]) return false;
    }
    return true;
  }
}
