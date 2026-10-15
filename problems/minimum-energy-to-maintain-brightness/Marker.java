import java.util.*;
class Marker {
    public long minEnergy(int n, int brightness, int[][] intervals) {
        long bulbs = (brightness + 2L) / 3;
        Arrays.sort(intervals, (a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));
        long covered = 0;
        long curS = intervals[0][0], curE = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= curE + 1) {
                curE = Math.max(curE, intervals[i][1]);
            } else {
                covered += curE - curS + 1;
                curS = intervals[i][0];
                curE = intervals[i][1];
            }
        }
        covered += curE - curS + 1;
        return bulbs * covered;
    }

    public boolean isCorrect(int n, int brightness, int[][] intervals, long output) {
        long expected = minEnergy(n, brightness, intervals);
        return expected == output;
    }
}
