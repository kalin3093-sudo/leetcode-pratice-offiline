import java.util.*;
class Marker {
  public int getWinner(int[] arr, int k) {
    int ans = arr[0];
    int wins = 0;

    for (int i = 1; i < arr.length && wins < k; ++i)
      if (arr[i] > ans) {
        ans = arr[i];
        wins = 1;
      } else {
        ++wins;
      }

    return ans;
  }

    public boolean isCorrect(int[] arr, int k, int output) {
        int expected = getWinner(arr, k);
        return expected == output;
    }
}
