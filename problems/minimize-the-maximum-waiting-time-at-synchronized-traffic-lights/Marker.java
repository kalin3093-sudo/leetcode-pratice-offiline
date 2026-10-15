import java.util.*;
class Marker {
    public int minPenalty(int period, int[] lights, int[] arrivalTime) {
        int maxGreen = 0;
        for (int x : lights) maxGreen = Math.max(maxGreen, x);
        int ans = 0;
        for (int t : arrivalTime) {
            int r = t % period;
            if (r >= maxGreen) ans = Math.max(ans, period - r);
        }
        return ans;
    }

    public boolean isCorrect(int period, int[] lights, int[] arrivalTime, int output) {
        return minPenalty(period, lights, arrivalTime) == output;
    }
}
