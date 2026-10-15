import java.util.*;
class Marker {
    public int sumDivisibleByK(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int x : nums) freq.merge(x, 1, Integer::sum);
        int ans = 0;
        for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
            if (e.getValue() % k == 0) ans += e.getKey() * e.getValue();
        }
        return ans;
    }
    public boolean isCorrect(int[] nums, int k, int output) {
        return output == sumDivisibleByK(nums, k);
    }
}
