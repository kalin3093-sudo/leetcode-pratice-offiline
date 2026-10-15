import java.util.*;
class Marker {
    public List<Integer> getGoodIndices(int[][] variables, int target) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < variables.length; i++) {
            int a = variables[i][0];
            int b = variables[i][1];
            int c = variables[i][2];
            int m = variables[i][3];
            long val = modPow(a, b, 10);
            val = modPow(val, c, m);
            if (val == target) ans.add(i);
        }
        return ans;
    }
    private long modPow(long a, long e, long mod) {
        if (mod == 1) return 0;
        long res = 1 % mod;
        a %= mod;
        while (e > 0) {
            if ((e & 1) == 1) res = (res * a) % mod;
            a = (a * a) % mod;
            e >>= 1;
        }
        return res;
    }

    public boolean isCorrect(int[][] variables, int target, List<Integer> output) {
        List<Integer> expected = getGoodIndices(variables, target);
        return expected.equals(output);
    }
}