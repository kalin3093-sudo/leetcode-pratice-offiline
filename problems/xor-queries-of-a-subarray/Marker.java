import java.util.*;
class Marker {
  public int[] xorQueries(int[] arr, int[][] queries) {
    int[] ans = new int[queries.length];
    int[] xors = new int[arr.length + 1];

    for (int i = 0; i < arr.length; ++i)
      xors[i + 1] = xors[i] ^ arr[i];

    int i = 0;
    for (int[] query : queries) {
      final int left = query[0];
      final int right = query[1];
      ans[i++] = xors[left] ^ xors[right + 1];
    }

    return ans;
  }

    public boolean isCorrect(int[] arr, int[][] queries, int[] output) {
        int[] expected = xorQueries(arr, queries);
        return Arrays.equals(expected, output);
    }
}
