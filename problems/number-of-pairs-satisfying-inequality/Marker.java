import java.util.*;

class Marker {
    private long[] a, tmp;
    private long diff;
    private long ans;

    public long numberOfPairs(int[] nums1, int[] nums2, int d) {
        int n = nums1.length;
        a = new long[n];
        tmp = new long[n];
        for (int i = 0; i < n; i++) a[i] = (long) nums1[i] - nums2[i];
        diff = d;
        ans = 0;
        sortCount(0, n - 1);
        return ans;
    }

    private void sortCount(int l, int r) {
        if (l >= r) return;
        int m = (l + r) >>> 1;
        sortCount(l, m);
        sortCount(m + 1, r);
        int j = m + 1;
        for (int i = l; i <= m; i++) {
            while (j <= r && a[i] > a[j] + diff) j++;
            ans += r - j + 1;
        }
        int i = l, k = m + 1, t = l;
        while (i <= m && k <= r) tmp[t++] = a[i] <= a[k] ? a[i++] : a[k++];
        while (i <= m) tmp[t++] = a[i++];
        while (k <= r) tmp[t++] = a[k++];
        for (int x = l; x <= r; x++) a[x] = tmp[x];
    }

    public boolean isCorrect(int[] nums1, int[] nums2, int d, long output) {
        return output == numberOfPairs(nums1, nums2, d);
    }
}
