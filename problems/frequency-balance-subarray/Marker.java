import java.util.*;
class Marker {
    public int getLength(int[] nums) {
        int n = nums.length, ans = 1;
        for (int i = 0; i < n; i++) {
            Map<Integer, Integer> freq = new HashMap<>();
            for (int j = i; j < n; j++) {
                freq.merge(nums[j], 1, Integer::sum);
                if (isBalanced(freq)) ans = Math.max(ans, j - i + 1);
            }
        }
        return ans;
    }
    boolean isBalanced(Map<Integer, Integer> freq) {
        if (freq.size() == 1) return true;
        Set<Integer> freqs = new HashSet<>(freq.values());
        if (freqs.size() != 2) return false;
        int f1 = freqs.iterator().next(), f2 = freqs.stream().skip(1).findFirst().orElse(0);
        if (f1 > f2) { int t = f1; f1 = f2; f2 = t; }
        return f2 == 2 * f1;
    }
    public boolean isCorrect(int[] nums, int output) {
        return getLength(nums) == output;
    }
}