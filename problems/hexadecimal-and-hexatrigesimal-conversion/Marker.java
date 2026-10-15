import java.util.*;
class Marker {
    public String concatHex36(int n) {
        long sq = 1L * n * n;
        long cb = 1L * n * n * n;
        return toBase(sq, 16) + toBase(cb, 36);
    }
    String toBase(long x, int base) {
        if (x == 0) return "0";
        StringBuilder sb = new StringBuilder();
        while (x > 0) {
            int d = (int)(x % base);
            sb.append(d < 10 ? (char)('0' + d) : (char)('A' + d - 10));
            x /= base;
        }
        return sb.reverse().toString();
    }
    public boolean isCorrect(int n, String output) {
        return concatHex36(n).equals(output);
    }
}