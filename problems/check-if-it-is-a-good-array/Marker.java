import java.util.*;
class Marker {
    public boolean isGoodArray(int[] nums) {
        int g = 0;
        for (int x : nums) g = gcd(g, x);
        return g == 1;
    }

    private static int gcd(int a, int b) {
        while (b != 0) { int t = a % b; a = b; b = t; }
        return a;
    }

    public boolean isCorrect(int[] nums, boolean output) {
        return isGoodArray(nums) == output;
    }
}
