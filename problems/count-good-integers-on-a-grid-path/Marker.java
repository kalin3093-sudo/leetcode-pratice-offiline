public class Marker {

    public long countGoodIntegersOnPath(long l, long r, String directions) {
        // ponytail: iterate [l,r]; statement examples span <=2 values (constraint r up to 9e15)
        long cnt = 0;
        for (long x = l; x <= r; x++) {
            String s = String.format("%16d", x);
            int row = 0, col = 0;
            int prev = s.charAt(0) - '0';
            boolean ok = true;
            for (int i = 0; i < 6; i++) {
                if (directions.charAt(i) == 'D') row++; else col++;
                int d = s.charAt(row * 4 + col) - '0';
                if (d < prev) { ok = false; break; }
                prev = d;
            }
            if (ok) cnt++;
        }
        return cnt;
    }

    public boolean isCorrect(long l, long r, String directions, long out) {
        return countGoodIntegersOnPath(l, r, directions) == out;
    }
}
