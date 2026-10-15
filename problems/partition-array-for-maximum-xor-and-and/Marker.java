import java.util.*;

class Marker {
    public long maximizeXorAndXor(int[] nums) {
        int n = nums.length;
        long best = 0;
        for (int mask = 0; mask < (1 << n); mask++) {
            long andVal = mask == 0 ? 0 : -1L;
            int s = 0;
            for (int i = 0; i < n; i++) {
                if (((mask >> i) & 1) == 1) andVal &= nums[i];
                else s ^= nums[i];
            }
            int[] basis = new int[31];
            for (int i = 0; i < n; i++) {
                if (((mask >> i) & 1) == 1) continue;
                int v = nums[i] & ~s;
                for (int b = 30; b >= 0; b--) {
                    if (((v >> b) & 1) == 0) continue;
                    if (basis[b] == 0) {
                        basis[b] = v;
                        break;
                    }
                    v ^= basis[b];
                }
            }
            int x = 0;
            for (int b = 30; b >= 0; b--) {
                if ((x ^ basis[b]) > x) x ^= basis[b];
            }
            long val = andVal + x + (s ^ x);
            if (val > best) best = val;
        }
        return best;
    }

    public boolean isCorrect(int[] nums, long output) {
        return output == maximizeXorAndXor(nums);
    }
}
