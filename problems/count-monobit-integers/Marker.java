public class Marker {

    public int countMonobit(int n) {
        int cnt = 0;
        for (int x = 0; x <= n; x++) {
            String s = Integer.toBinaryString(x);
            boolean ok = true;
            for (int i = 1; i < s.length(); i++) {
                if (s.charAt(i) != s.charAt(0)) { ok = false; break; }
            }
            if (ok) cnt++;
        }
        return cnt;
    }

    public boolean isCorrect(int n, int out) {
        return countMonobit(n) == out;
    }
}
