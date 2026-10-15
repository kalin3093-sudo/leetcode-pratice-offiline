public class Marker {
    static boolean good(long x) {
        String s = String.valueOf(x);
        boolean inc = true, dec = true;
        for (int i = 1; i < s.length(); i++) {
            char a = s.charAt(i - 1), c = s.charAt(i);
            if (a >= c) inc = false;
            if (a <= c) dec = false;
        }
        return inc || dec;
    }

    public long countFancy(long l, long r) {
        // ponytail: iterate [l,r]; statement examples span <=2 values (constraint r-l up to 1e15)
        long cnt = 0;
        for (long x = l; x <= r; x++) {
            if (good(x)) { cnt++; continue; }
            long ds = 0;
            for (long y = x; y > 0; y /= 10) ds += y % 10;
            if (good(ds)) cnt++;
        }
        return cnt;
    }

    public boolean isCorrect(long l, long r, long out) {
        return countFancy(l, r) == out;
    }
}
