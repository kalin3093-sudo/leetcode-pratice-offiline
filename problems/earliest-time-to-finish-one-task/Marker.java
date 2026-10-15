import java.util.*;
class Marker {
    public int earliestTime(int[][] tasks) {
        int ans = Integer.MAX_VALUE;
        for (int[] t : tasks) {
            ans = Math.min(ans, t[0] + t[1]);
        }
        return ans;
    }

    public boolean isCorrect(int[][] tasks, int output) {
        return earliestTime(tasks) == output;
    }
}