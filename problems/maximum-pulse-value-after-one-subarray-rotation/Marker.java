import java.util.*;

class Marker {
    public long maxValue(int[] nums) {
        int n = nums.length;
        long[] P = new long[n + 1];
        for (int i = 0; i < n; i++) {
            P[i + 1] = P[i] + (i % 2 == 0 ? nums[i] : -nums[i]);
        }
        long base = P[n];
        
        long[] maxP = new long[2];
        long[] maxPNext = new long[2];
        Arrays.fill(maxP, Long.MIN_VALUE);
        Arrays.fill(maxPNext, Long.MIN_VALUE);
        
        maxP[0] = P[0];
        maxPNext[0] = P[1];
        
        long bestGain = 0;
        for (int r = 1; r < n; r++) {
            int rParity = r % 2;
            long gain;
            // l and r same parity: use P[l+1]
            if (maxPNext[rParity] != Long.MIN_VALUE) {
                gain = 2 * (maxPNext[rParity] - P[r + 1]);
                bestGain = Math.max(bestGain, gain);
            }
            // l and r different parity: use P[l]
            if (maxP[rParity ^ 1] != Long.MIN_VALUE) {
                gain = 2 * (maxP[rParity ^ 1] - P[r + 1]);
                bestGain = Math.max(bestGain, gain);
            }
            
            // Update for future r
            int lParity = r % 2;
            maxP[lParity] = Math.max(maxP[lParity], P[r]);
            maxPNext[lParity] = Math.max(maxPNext[lParity], P[r + 1]);
        }
        
        return base + bestGain;
    }

    public boolean isCorrect(int[] nums, long output) {
        return output == maxValue(nums);
    }
}