import java.util.*;

class Marker {
    static final int MOD = 1000000007;

    public int numSub(String s) {
        long res = 0, cur = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                cur++;
                res = (res + cur) % MOD;
            } else {
                cur = 0;
            }
        }
        return (int) res;
    }

    public boolean isCorrect(String s, int output) {
        return output == numSub(s);
    }
}
