import java.util.*;

class Marker {
    public int countIntersectingIntervals(int[][] intervals) {
        int n = intervals.length, ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (Math.max(intervals[i][0], intervals[j][0]) <= Math.min(intervals[i][1], intervals[j][1])) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public boolean isCorrect(int[][] intervals, int output) {
        return output == countIntersectingIntervals(intervals);
    }
}
