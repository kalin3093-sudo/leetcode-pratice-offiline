import java.util.*;
class Marker {
    public boolean checkPrimeFrequency(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int x : nums) freq.put(x, freq.getOrDefault(x, 0) + 1);
        for (int c : freq.values()) if (isPrime(c)) return true;
        return false;
    }

    private static boolean isPrime(int x) {
        if (x < 2) return false;
        for (int i = 2; i * i <= x; i++) if (x % i == 0) return false;
        return true;
    }

    public boolean isCorrect(int[] nums, boolean output) {
        return checkPrimeFrequency(nums) == output;
    }
}
