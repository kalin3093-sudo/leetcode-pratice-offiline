import java.util.*;
class Marker {
  public int[] findThePrefixCommonArray(int[] A, int[] B) {
    final int n = A.length;
    int prefixCommon = 0;
    int[] ans = new int[n];
    int[] count = new int[n + 1];

    for (int i = 0; i < A.length; ++i) {
      if (++count[A[i]] == 2)
        ++prefixCommon;
      if (++count[B[i]] == 2)
        ++prefixCommon;
      ans[i] = prefixCommon;
    }

    return ans;
  }

    public boolean isCorrect(int[] A, int[] B, int[] output) {
        int[] expected = findThePrefixCommonArray(A, B);
        return Arrays.equals(expected, output);
    }
}
