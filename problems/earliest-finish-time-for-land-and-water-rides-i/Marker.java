import java.util.*;
class Marker {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < landStartTime.length; i++) {
            for (int j = 0; j < waterStartTime.length; j++) {
                int t1 = Math.max(landStartTime[i] + landDuration[i], waterStartTime[j]) + waterDuration[j];
                int t2 = Math.max(waterStartTime[j] + waterDuration[j], landStartTime[i]) + landDuration[i];
                ans = Math.min(ans, Math.min(t1, t2));
            }
        }
        return ans;
    }

    public boolean isCorrect(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration, int output) {
        return earliestFinishTime(landStartTime, landDuration, waterStartTime, waterDuration) == output;
    }
}