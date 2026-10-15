public class Marker {

    public int countDigitOccurrences(int[] nums, int digit) {
        int total = 0;
        for (int x : nums) {
            for (int t = x; t > 0; t /= 10) {
                if (t % 10 == digit) total++;
            }
        }
        return total;
    }

    public boolean isCorrect(int[] nums, int digit, int out) {
        return countDigitOccurrences(nums, digit) == out;
    }
}
