import java.util.*;
class Marker {
    public long minOperations(int[] nums1, int[] nums2) {
        long base = 0;
        for (int i = 0; i < nums1.length; i++) base += Math.abs((long) nums1[i] - nums2[i]);
        int a = nums2[nums2.length - 1];
        long extra = Long.MAX_VALUE;
        for (int i = 0; i < nums1.length; i++) {
            int lo = Math.min(nums1[i], nums2[i]);
            int hi = Math.max(nums1[i], nums2[i]);
            long d = a < lo ? lo - a : (a > hi ? a - hi : 0);
            extra = Math.min(extra, d);
        }
        return base + 1 + extra;
    }

    public boolean isCorrect(int[] nums1, int[] nums2, long output) {
        return output == minOperations(nums1, nums2);
    }
}
