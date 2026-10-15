import java.util.*;
class Marker {
    private static final int MOD = 1000000007;
    private static final int MAXV = 100002;

    public int createSortedArray(int[] instructions) {
        int[] bit = new int[MAXV + 2];
        long cost = 0;
        int count = 0;
        for (int x : instructions) {
            int less = query(bit, x - 1);
            int greater = count - query(bit, x);
            cost = (cost + Math.min(less, greater)) % MOD;
            add(bit, x, 1);
            count++;
        }
        return (int) cost;
    }

    private void add(int[] bit, int i, int d) {
        for (; i < bit.length; i += i & -i) bit[i] += d;
    }

    private int query(int[] bit, int i) {
        int s = 0;
        for (; i > 0; i -= i & -i) s += bit[i];
        return s;
    }

    public boolean isCorrect(int[] instructions, int output) {
        return createSortedArray(instructions) == output;
    }
}
