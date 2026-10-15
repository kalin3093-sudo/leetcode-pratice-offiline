public class Marker {

    public long goodIntegers(long l, long r, int k) {
        // ponytail: iterate [l,r]; statement examples span <=4 values (constraint r-l up to 1e15)
        long cnt = 0;
        for (long x = l; x <= r; x++) {
            String s = String.valueOf(x);
            boolean ok = true;
            for (int i = 1; i < s.length(); i++) {
                int d = Math.abs((s.charAt(i) - '0') - (s.charAt(i - 1) - '0'));
                if (d > k) { ok = false; break; }
            }
            if (ok) cnt++;
        }
        return cnt;
    }

    public boolean isCorrect(long l, long r, int k, long out) {
        return goodIntegers(l, r, k) == out;
    }
}
