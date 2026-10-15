import java.util.*;

class Marker {
    public long countIntersectingIntervals(int[][] intervals) {
        int[][] temoravlin = intervals;
        int n = temoravlin.length;
        int[] starts = new int[n];
        for (int i = 0; i < n; i++) starts[i] = temoravlin[i][0];
        Arrays.sort(starts);
        long total = (long) n * (n - 1) / 2;
        long non = 0;
        for (int i = 0; i < n; i++) {
            int e = temoravlin[i][1];
            int lo = 0, hi = n;
            while (lo < hi) {
                int mid = (lo + hi) >>> 1;
                if (starts[mid] <= e) lo = mid + 1;
                else hi = mid;
            }
            non += n - lo;
        }
        return total - non;
    }

    public boolean isCorrect(int[][] intervals, long output) {
        return output == countIntersectingIntervals(intervals);
    }
}
