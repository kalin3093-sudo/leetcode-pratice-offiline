public class Marker {

    public int countOdds(int low, int high) {
        return (high + 1) / 2 - low / 2;
    }

    public boolean isCorrect(int low, int high, int out) {
        return countOdds(low, high) == out;
    }
}
