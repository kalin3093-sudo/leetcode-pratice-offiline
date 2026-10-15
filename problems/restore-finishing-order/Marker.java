import java.util.*;

class Marker {
  public int[] recoverOrder(int[] order, int[] friends) {
    Set<Integer> set = new HashSet<>();
    for (int f : friends)
      set.add(f);
    List<Integer> res = new ArrayList<>();
    for (int x : order)
      if (set.contains(x))
        res.add(x);
    int[] ans = new int[res.size()];
    for (int i = 0; i < ans.length; i++)
      ans[i] = res.get(i);
    return ans;
  }

  public boolean isCorrect(int[] order, int[] friends, int[] output) {
    return output != null && Arrays.equals(recoverOrder(order, friends), output);
  }
}
