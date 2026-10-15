import java.util.*;
class Marker {
    public int minMaxWaitingTime(int[] demand, int[] fuel) {
        int n = demand.length;
        // dp[i][f0][f1][t0][t1] = min max wait time after serving i cars
        // But state space is too large. Use BFS/DP with state compression.
        // State: (car_idx, fuel0, fuel1, busy0, busy1) -> min max_wait
        // Since demand.length <= 50, fuel[i] <= 50, we can use memoization
        
        Map<Long, Integer> memo = new HashMap<>();
        
        // Initial state: car 0 becomes allowed at time 0
        // Both dispensers free at time 0
        int result = dfs(0, demand, fuel[0], fuel[1], 0, 0, 0, memo);
        return result == Integer.MAX_VALUE ? -1 : result;
    }
    
    // Returns min max_wait for remaining cars starting from car idx
    // busy0, busy1 = time until dispenser becomes free
    // maxWaitSoFar = maximum waiting time encountered so far
    private int dfs(int carIdx, int[] demand, int f0, int f1, int busy0, int busy1, int maxWaitSoFar, Map<Long, Integer> memo) {
        int n = demand.length;
        if (carIdx == n) return maxWaitSoFar;
        
        // Encode state
        long key = (((((long)carIdx * 51 + f0) * 51 + f1) * 51 + busy0) * 51 + busy1) * 51 + maxWaitSoFar;
        if (memo.containsKey(key)) return memo.get(key);
        
        int d = demand[carIdx];
        int best = Integer.MAX_VALUE;
        
        // Try dispenser 0
        if (f0 >= d) {
            int wait = Math.max(0, busy0);
            int newMaxWait = Math.max(maxWaitSoFar, wait);
            int newBusy0 = d;
            int newBusy1 = Math.max(0, busy1 - wait);
            int res = dfs(carIdx + 1, demand, f0 - d, f1, newBusy0, newBusy1, newMaxWait, memo);
            best = Math.min(best, res);
        }
        
        // Try dispenser 1
        if (f1 >= d) {
            int wait = Math.max(0, busy1);
            int newMaxWait = Math.max(maxWaitSoFar, wait);
            int newBusy1 = d;
            int newBusy0 = Math.max(0, busy0 - wait);
            int res = dfs(carIdx + 1, demand, f0, f1 - d, newBusy0, newBusy1, newMaxWait, memo);
            best = Math.min(best, res);
        }
        
        memo.put(key, best);
        return best;
    }

    public boolean isCorrect(int[] demand, int[] fuel, int output) {
        return minMaxWaitingTime(demand, fuel) == output;
    }
}