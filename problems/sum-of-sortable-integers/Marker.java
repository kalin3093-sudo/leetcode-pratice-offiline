import java.util.*;

class Marker {
  public int sortableIntegers(int[] nums) {
    int n = nums.length;
    int[] s = nums.clone();
    Arrays.sort(s);
    int total = 0;
    for (int k = 1; k <= n; k++) {
      if (n % k != 0) continue;
      boolean ok = true;
      for (int i = 0; i < n; i += k) {
        if (!isRotation(Arrays.copyOfRange(nums, i, i + k), Arrays.copyOfRange(s, i, i + k))) {
          ok = false;
          break;
        }
      }
      if (ok) total += k;
    }
    return total;
  }

  public boolean isCorrect(int[] nums, int output) {
    return sortableIntegers(nums) == output;
  }

  private boolean isRotation(int[] block, int[] target) {
    int k = block.length;
    int[] text = new int[2 * k];
    System.arraycopy(block, 0, text, 0, k);
    System.arraycopy(block, 0, text, k, k);
    int[] lps = new int[k];
    for (int i = 1, len = 0; i < k; ) {
      if (target[i] == target[len]) {
        len++;
        lps[i++] = len;
      } else if (len != 0) {
        len = lps[len - 1];
      } else {
        lps[i++] = 0;
      }
    }
    for (int i = 0, j = 0; i < text.length; ) {
      if (text[i] == target[j]) {
        i++;
        j++;
        if (j == k) return true;
      } else if (j != 0) {
        j = lps[j - 1];
      } else {
        i++;
      }
    }
    return false;
  }
}
