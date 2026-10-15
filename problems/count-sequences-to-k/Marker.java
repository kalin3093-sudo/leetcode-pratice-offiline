import java.util.*;

class Marker {
    private static final int MOD = 1_000_000_007;

    public int countSequences(int[] nums, long k) {
        int[] kExp = factorK(k);
        if (kExp == null) return 0;
        int k2 = kExp[0], k3 = kExp[1], k5 = kExp[2];

        int[][] factors = new int[nums.length][3];
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            while (x % 2 == 0) { factors[i][0]++; x /= 2; }
            while (x % 3 == 0) { factors[i][1]++; x /= 3; }
            while (x % 5 == 0) { factors[i][2]++; x /= 5; }
        }

        Map<Long, Integer> dp = new HashMap<>();
        dp.put(encode(0, 0, 0), 1);

        for (int i = 0; i < nums.length; i++) {
            Map<Long, Integer> next = new HashMap<>();
            int a = factors[i][0], b = factors[i][1], c = factors[i][2];
            for (Map.Entry<Long, Integer> e : dp.entrySet()) {
                long key = e.getKey();
                int cnt = e.getValue();
                int x2 = (int)(key >> 40);
                int x3 = (int)((key >> 20) & 0xFFFFF);
                int x5 = (int)(key & 0xFFFFF);
                x2 = fromSigned(x2); x3 = fromSigned(x3); x5 = fromSigned(x5);

                add(next, x2 + a, x3 + b, x5 + c, cnt);
                add(next, x2 - a, x3 - b, x5 - c, cnt);
                add(next, x2, x3, x5, cnt);
            }
            dp = next;
        }

        return dp.getOrDefault(encode(k2, k3, k5), 0);
    }

    private void add(Map<Long, Integer> map, int x2, int x3, int x5, int val) {
        long key = encode(x2, x3, x5);
        map.put(key, (map.getOrDefault(key, 0) + val) % MOD);
    }

    private long encode(int x2, int x3, int x5) {
        return ((long)toSigned(x2) << 40) | ((long)toSigned(x3) << 20) | toSigned(x5);
    }

    private int toSigned(int x) {
        return x + 100000;
    }

    private int fromSigned(int x) {
        return x - 100000;
    }

    private int[] factorK(long k) {
        int[] exp = new int[3];
        long orig = k;
        while (k % 2 == 0) { exp[0]++; k /= 2; }
        while (k % 3 == 0) { exp[1]++; k /= 3; }
        while (k % 5 == 0) { exp[2]++; k /= 5; }
        if (k != 1) return null;
        return exp;
    }

    public boolean isCorrect(int[] nums, long k, int output) {
        return output == countSequences(nums, k);
    }
}