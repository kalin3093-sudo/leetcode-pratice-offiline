import java.util.*;
class Marker {
  public List<Integer> lexicalOrder(int n) {
    List<Integer> ans = new ArrayList<>();
    int curr = 1;

    while (ans.size() < n) {
      ans.add(curr);
      if (curr * 10 <= n) {
        curr *= 10;
      } else {
        while (curr % 10 == 9 || curr == n)
          curr /= 10;
        ++curr;
      }
    }

    return ans;
  }

    public boolean isCorrect(int n, List<Integer> output) {
        List<Integer> expected = lexicalOrder(n);
        return expected.equals(output);
    }
}
