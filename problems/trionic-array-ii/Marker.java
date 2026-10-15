import java.util.*;

class Marker {
    private static final long NEG = Long.MIN_VALUE / 4;

    public long maxSumTrionic(int[] nums) {
        int n = nums.length;
        long inc = NEG;
        long dec = NEG;
        long fin = NEG;
        long best = NEG;
        for (int i = 1; i < n; i++) {
            long ninc = NEG;
            if (nums[i] > nums[i - 1]) ninc = Math.max(inc, nums[i - 1]) + nums[i];
            long ndec = NEG;
            if (nums[i] < nums[i - 1]) ndec = Math.max(dec, inc) + nums[i];
            long nfin = NEG;
            if (nums[i] > nums[i - 1]) nfin = Math.max(fin, dec) + nums[i];
            inc = ninc;
            dec = ndec;
            fin = nfin;
            if (fin > best) best = fin;
        }
        return best;
    }

    public boolean isCorrect(int[] nums, long output) {
        return output == maxSumTrionic(nums);
    }
}
