import java.util.*;
class Marker {
  public int[] advantageCount(int[] nums1, int[] nums2) {
    TreeMap<Integer, Integer> map = new TreeMap<>();

    for (final int num : nums1)
      map.merge(num, 1, Integer::sum);

    for (int i = 0; i < nums2.length; i++) {
      Integer key = map.higherKey(nums2[i]);
      if (key == null)
        key = map.firstKey();
      if (map.merge(key, -1, Integer::sum) == 0)
        map.remove(key);
      nums1[i] = key;
    }

    return nums1;
  }

    public boolean isCorrect(int[] nums1, int[] nums2, int[] output) {
        int[] expected = advantageCount(nums1, nums2);
        return Arrays.equals(expected, output);
    }
}
