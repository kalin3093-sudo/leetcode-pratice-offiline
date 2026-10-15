public class Marker {

    public int countCommas(int n) {
        int total = 0;
        for (int x = 1; x <= n; x++) {
            int digits = 1;
            long p = 10;
            while (p <= x) {
                digits++;
                p *= 10;
            }
            if (digits >= 4) total += (digits - 1) / 3;
        }
        return total;
    }

    public boolean isCorrect(int n, int out) {
        return countCommas(n) == out;
    }
}
