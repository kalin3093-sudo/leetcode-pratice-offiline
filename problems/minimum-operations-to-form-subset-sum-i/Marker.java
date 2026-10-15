import java.util.*;

class Marker {
    public int minOperations(int[] nums, int sum) {
        int n = nums.length;
        List<int[]> choices = new ArrayList<>();
        
        for (int x : nums) {
            Map<Integer, Integer> costMap = new HashMap<>();
            for (int a = 0; a <= 20; a++) {
                long mul = (long) x << a;
                for (int b = 0; b <= 20; b++) {
                    int v = (int) (mul >> b);
                    if (v <= sum) costMap.merge(v, a + b, Math::min);
                    if (v == 0) break;
                }
            }
            int[] arr = new int[sum + 1];
            Arrays.fill(arr, Integer.MAX_VALUE);
            for (Map.Entry<Integer, Integer> e : costMap.entrySet()) {
                if (e.getKey() <= sum) {
                    arr[e.getKey()] = e.getValue();
                }
            }
            choices.add(arr);
        }
        
        int[] dp = new int[sum + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        
        for (int[] costs : choices) {
            int[] newDp = dp.clone();
            for (int s = sum; s >= 0; s--) {
                if (dp[s] == Integer.MAX_VALUE) continue;
                for (int v = 1; v <= sum - s; v++) {
                    if (costs[v] == Integer.MAX_VALUE) continue;
                    newDp[s + v] = Math.min(newDp[s + v], dp[s] + costs[v]);
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