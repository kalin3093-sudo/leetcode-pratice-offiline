import java.util.*;
class Marker {
  public int longestSubsequence(int[] arr, int difference) {
    int ans = 0;
    Map<Integer, Integer> lengthAt = new HashMap<>();

    for (final int a : arr) {
      lengthAt.put(a, lengthAt.getOrDefault(a - difference, 0) + 1);
      ans = Math.max(ans, lengthAt.get(a));
    }

    return ans;
  }

    public boolean isCorrect(int[] arr, int difference, int output) {
        int expected = longestSubsequence(arr, difference);
        return expected == output;
    }
}
