import java.util.*;
class Marker {
  public List<Integer> addToArrayForm(int[] num, int k) {
    List<Integer> ans = new LinkedList<>();

    for (int i = num.length - 1; i >= 0; --i) {
      ans.add(0, (num[i] + k) % 10);
      k = (num[i] + k) / 10;
    }

    while (k > 0) {
      ans.add(0, k % 10);
      k /= 10;
    }

    return ans;
  }

    public boolean isCorrect(int[] num, int k, List<Integer> output) {
        List<Integer> expected = addToArrayForm(num, k);
        return expected.equals(output);
    }
}
