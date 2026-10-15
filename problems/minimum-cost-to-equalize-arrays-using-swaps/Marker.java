import java.util.*;
class Marker {
    public int minCost(int[] nums1, int[] nums2) {
        int maxVal = 80000;
        int[] cnt1 = new int[maxVal + 1];
        int[] cnt2 = new int[maxVal + 1];
        for (int x : nums1) cnt1[x]++;
        for (int x : nums2) cnt2[x]++;

        long diffSum = 0;
        for (int v = 1; v <= maxVal; v++) {
            int total = cnt1[v] + cnt2[v];
            if (total % 2 != 0) return -1;
            diffSum += Math.abs(cnt1[v] - cnt2[v]);
        }
        return (int)(diffSum / 4);
    }

    public boolean isCorrect(int[] nums1, int[] nums2, int output) {
        int expected = minCost(nums1, nums2);
        return expected == output;
    }
}