import java.util.*;
class Marker {
    public int minimumPairRemoval(int[] nums) {
        ArrayList<Long> a = new ArrayList<>();
        for (int v : nums) a.add((long) v);
        int ops = 0;
        while (true) {
            boolean ok = true;
            for (int i = 0; i + 1 < a.size(); i++) if (a.get(i) > a.get(i + 1)) { ok = false; break; }
            if (ok) return ops;
            int idx = 0;
            long best = a.get(0) + a.get(1);
            for (int i = 1; i < a.size() - 1; i++) {
                long s = a.get(i) + a.get(i + 1);
                if (s < best) { best = s; idx = i; }
            }
            a.set(idx, best);
            a.remove(idx + 1);
            ops++;
        }
    }

    public boolean isCorrect(int[] nums, int output) {
        return output == minimumPairRemoval(nums);
    }
}
