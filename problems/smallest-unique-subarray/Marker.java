import java.util.*;
class Marker {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        for (int L = 1; L <= n; L++) {
            Map<String, Integer> cnt = new HashMap<>();
            for (int i = 0; i + L <= n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = i; j < i + L; j++) sb.append(nums[j]).append('#');
                cnt.merge(sb.toString(), 1, Integer::sum);
            }
            for (int v : cnt.values()) if (v == 1) return L;
        }
        return n;
    }

    public boolean isCorrect(int[] nums, int output) {
        return smallestUniqueSubarray(nums) == output;
    }
}
