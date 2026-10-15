import java.util.*;

class Marker {
  public int reversePairs(int[] nums) {
    int[] a = nums.clone();
    return mergeSort(a, 0, a.length - 1);
  }

  private int mergeSort(int[] a, int l, int r) {
    if (l >= r)
      return 0;
    int mid = (l + r) / 2;
    int cnt = mergeSort(a, l, mid) + mergeSort(a, mid + 1, r);
    int j = mid + 1;
    for (int i = l; i <= mid; i++) {
      while (j <= r && (long) a[i] > 2L * a[j])
        j++;
      cnt += j - (mid + 1);
    }
    int[] tmp = new int[r - l + 1];
    int p = l, q = mid + 1, t = 0;
    while (p <= mid && q <= r)
      tmp[t++] = a[p] <= a[q] ? a[p++] : a[q++];
    while (p <= mid)
      tmp[t++] = a[p++];
    while (q <= r)
      tmp[t++] = a[q++];
    for (int i = l; i <= r; i++)
      a[i] = tmp[i - l];
    return cnt;
  }

  public boolean isCorrect(int[] nums, int output) {
    return output == reversePairs(nums);
  }
}
