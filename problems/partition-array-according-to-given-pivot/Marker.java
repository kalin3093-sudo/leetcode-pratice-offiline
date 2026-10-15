import java.util.*;
class Marker {
  public int[] pivotArray(int[] nums, int pivot) {
    int[] ans = new int[nums.length];
    int i = 0; // ans' index

    for (final int num : nums)
      if (num < pivot)
        ans[i++] = num;

    for (final int num : nums)
      if (num == pivot)
        ans[i++] = num;

    for (final int num : nums)
      if (num > pivot)
        ans[i++] = num;

    return ans;
  }

    public boolean isCorrect(int[] nums, int pivot, int[] output) {
        int[] expected = pivotArray(nums, pivot);
        return Arrays.equals(expected, output);
    }
}
