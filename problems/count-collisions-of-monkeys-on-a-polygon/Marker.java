public class Marker {
    static final int MOD = 1000000007;

    public int monkeyMove(int n) {
        long p = 1, base = 2;
        int e = n;
        while (e > 0) {
            if ((e & 1) == 1) p = p * base % MOD;
            base = base * base % MOD;
            e >>= 1;
        }
        return (int) ((p - 2 + MOD) % MOD);
    }

    public boolean isCorrect(int n, int out) {
        return monkeyMove(n) == out;
    }
}
