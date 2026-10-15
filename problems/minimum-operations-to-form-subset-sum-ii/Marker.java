import java.util.*;

class Marker {
    public int minOperations(int[] nums, int sum) {
        int maxVal = sum;
        List<int[]> reachable = new ArrayList<>();
        
        for (int x : nums) {
            Map<Integer, Integer> costMap = new HashMap<>();
            for (int d = 0; d <= 20; d++) {
                int base = x >> d;
                if (base == 0) break;
                for (int j = 0; j <= 20; j++) {
                    int v = base << j;
                    if (v <= maxVal) costMap.merge(v, d + j, Math::min);
                    if (v > maxVal) break;
                }
            }
            int[] arr = new int[maxVal + 1];
            Arrays.fill(arr, Integer.MAX_VALUE);
            for (Map.Entry<Integer, Integer> e : costMap.entrySet()) {
                arr[e.getKey()] = e.getValue();
            }
            reachable.add(arr);
        }
        
        int[] dp = new int[sum + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        
        for (int[] costs : reachable) {
            int[] newDp = dp.clone();
            for (int s = 0; s <= sum; s++) {
                if (dp[s] == Integer.MAX_VALUE) continue;
                for (int v = 1; v <= sum; v++) {
                    if (costs[v] == Integer.MAX_VALUE) continue;
                    if (s + v <= sum) {
                        newDp[s + v] = Math.min(newDp[s + v], dp[s] + costs[v]);
                    }
                }
            }
            dp = newDp;
        }
        
        return dp[sum] == Integer.MAX_VALUE ? -1 : dp[sum];
    }

    public boolean isCorrect(int[] nums, int sum, int output) {
        return minOperations(nums, sum) == output;
    }
}