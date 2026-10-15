import java.util.*;
class Marker {
    public int[] makeParityAlternating(int[] nums) {
        int bestM = -1, bestD = 0;
        for (int p = 0; p <= 1; p++) {
            ArrayList<Integer> fixed = new ArrayList<>();
            ArrayList<Integer> flipped = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                int req = ((p + i) % 2 + 2) % 2;
                int act = ((nums[i] % 2) + 2) % 2;
                if (req == act) fixed.add(nums[i]);
                else flipped.add(nums[i]);
            }
            int m = flipped.size();
            int minD;
            if (flipped.isEmpty()) {
                int lo = fixed.get(0), hi = fixed.get(0);
                for (int v : fixed) { lo = Math.min(lo, v); hi = Math.max(hi, v); }
                minD = hi - lo;
            } else {
                int fMin = Integer.MAX_VALUE, fMax = Integer.MIN_VALUE;
                for (int v : fixed) { fMin = Math.min(fMin, v); fMax = Math.max(fMax, v); }
                int maxFl = Collections.max(flipped), minFl = Collections.min(flipped);
                boolean d0 = false;
                if (fixed.isEmpty()) {
                    HashSet<Integer> s = new HashSet<>(flipped);
                    d0 = s.size() == 1 || (s.size() == 2 && Collections.max(s) - Collections.min(s) == 2);
                } else if (fMin == fMax) {
                    int L = fMin;
                    d0 = true;
                    for (int a : flipped) if (a != L - 1 && a != L + 1) { d0 = false; break; }
                }
                int top = Math.max(fixed.isEmpty() ? Integer.MIN_VALUE : fMax, maxFl - 1);
                int bot = Math.min(fixed.isEmpty() ? Integer.MAX_VALUE : fMin, minFl + 1);
                int d1 = top - bot;
                minD = d1 >= 1 ? d1 : (d0 ? 0 : 1);
            }
            if (bestM < 0 || m < bestM || (m == bestM && minD < bestD)) {
                bestM = m; bestD = minD;
            }
        }
        return new int[] { bestM, bestD };
    }

    public boolean isCorrect(int[] nums, int[] output) {
        if (output == null || output.length != 2) return false;
        int[] exp = makeParityAlternating(nums);
        return output[0] == exp[0] && output[1] == exp[1];
    }
}
