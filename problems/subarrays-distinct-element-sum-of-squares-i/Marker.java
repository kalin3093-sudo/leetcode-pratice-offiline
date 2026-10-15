import java.util.*;
class Marker {
    public int sumCounts(List<Integer> nums) {
        int n = nums.size(), ans = 0;
        for (int i = 0; i < n; i++) {
            Set<Integer> seen = new HashSet<>();
            for (int j = i; j < n; j++) {
                seen.add(nums.get(j));
                ans += seen.size() * seen.size();
            }
        }
        return ans;
    }
    public boolean isCorrect(List<Integer> nums, int output) {
        return output == sumCounts(nums);
    }
}
