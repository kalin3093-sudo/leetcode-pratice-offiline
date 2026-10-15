import java.util.*;
class Marker {
    public long minOperations(int[] nums) {
        ArrayList<Long> evenPals = new ArrayList<>();
        ArrayList<Long> oddPals = new ArrayList<>();
        for (int len = 1; len <= 10; len++) {
            int halfLen = (len + 1) / 2;
            long start = (long) Math.pow(10, halfLen - 1);
            long end = (long) Math.pow(10, halfLen);
            for (long h = start; h < end; h++) {
                String hs = Long.toString(h);
                String lhs = new StringBuilder(hs).reverse().toString();
                String ps = (len % 2 == 0) ? hs + lhs : hs + lhs.substring(1);
                long p = Long.parseLong(ps);
                if (p > 2_000_000_100L) continue;
                if (p % 2 == 0) evenPals.add(p);
                else oddPals.add(p);
            }
        }
        Collections.sort(evenPals);
        Collections.sort(oddPals);
        long total = 0;
        for (int x : nums) {
            ArrayList<Long> list = (x % 2 == 0) ? evenPals : oddPals;
            int idx = Collections.binarySearch(list, (long) x);
            long best = Long.MAX_VALUE;
            if (idx >= 0) {
                total += 0;
                continue;
            }
            int ins = -idx - 1;
            if (ins < list.size()) best = Math.min(best, Math.abs(list.get(ins) - x));
            if (ins > 0) best = Math.min(best, Math.abs(list.get(ins - 1) - x));
            total += best / 2;
        }
        return total;
    }

    public boolean isCorrect(int[] nums, long output) {
        return output == minOperations(nums);
    }
}
