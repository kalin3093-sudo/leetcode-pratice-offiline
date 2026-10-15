import java.util.*;

class Marker {
    public int numIdenticalPairs(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        int ans = 0;
        for (int v : nums) {
            int c = cnt.getOrDefault(v, 0);
            ans += c;
            cnt.put(v, c + 1);
        }
        return ans;
    }

    public boolean isCorrect(int[] nums, int output) {
        return output == numIdenticalPairs(nums);
    }
}
