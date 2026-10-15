import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public int kIncreasing(int[] arr, int k) {
    int ans = 0;

    for (int i = 0; i < k; ++i) {
      List<Integer> sub = new ArrayList<>();
      for (int j = i; j < arr.length; j += k)
        sub.add(arr[j]);
      ans += numReplaced(sub);
    }

    return ans;
  }

  private int numReplaced(List<Integer> arr) {
    List<Integer> tails = new ArrayList<>();
    for (final int a : arr)
      if (tails.isEmpty() || tails.get(tails.size() - 1) <= a)
        tails.add(a);
      else
        tails.set(firstGreater(tails, a), a);
    return arr.size() - tails.size();
  }

  private int firstGreater(List<Integer> arr, int target) {
    final int i = Collections.binarySearch(arr, target + 1);
    return i < 0 ? -i - 1 : i;
  }

    public boolean isCorrect(int[] arr, int k, int output) {
        int expected = kIncreasing(arr, k);
        return expected == output;
    }
}
