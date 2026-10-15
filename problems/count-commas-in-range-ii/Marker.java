public class Marker {

    public long countCommas(long n) {
        long total = 0;
        long p = 1000;
        while (p <= n) {
            total += n - p + 1;
            p *= 1000;
        }
        return total;
    }

    public boolean isCorrect(long n, long out) {
        return countCommas(n) == out;
    }
}
