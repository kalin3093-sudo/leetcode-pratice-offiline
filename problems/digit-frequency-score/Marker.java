import java.util.*;
class Marker {
    public int digitFrequencyScore(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

    public boolean isCorrect(int n, int output) {
        return digitFrequencyScore(n) == output;
    }
}
