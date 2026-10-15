import java.util.*;
class Marker {
    public int alternatingXOR(int[] nums, int target1, int target2) {
        final int MOD = 1000000007;
        int n = nums.length;
        
        // prefix XOR
        int[] pref = new int[n + 1];
        for (int i = 0; i < n; i++) {
            pref[i + 1] = pref[i] ^ nums[i];
        }
        
        // dp0[i] = number of ways to partition prefix[0..i] ending with target1 (odd blocks)
        // dp1[i] = number of ways to partition prefix[0..i] ending with target2 (even blocks)
        long[] dp0 = new long[n + 1];
        long[] dp1 = new long[n + 1];
        
        // Map from prefix XOR value to sum of dp values
        Map<Integer, Long> sum0 = new HashMap<>(); // sum of dp0 for prefix XOR = val
        Map<Integer, Long> sum1 = new HashMap<>(); // sum of dp1 for prefix XOR = val
        
        // Empty prefix: 0 blocks (even), ready for target1
        sum1.put(0, 1L);
        
        for (int i = 1; i <= n; i++) {
            int x = pref[i];
            
            // To end with target1 at position i, we need a block XOR = target1
            // So pref[i] ^ pref[j] = target1 => pref[j] = pref[i] ^ target1
            // Previous partition must end with target2 (even blocks)
            int need0 = x ^ target1;
            dp0[i] = sum1.getOrDefault(need0, 0L) % MOD;
            
            // To end with target2 at position i, we need a block XOR = target2
            // Previous partition must end with target1 (odd blocks)
            int need1 = x ^ target2;
            dp1[i] = sum0.getOrDefault(need1, 0L) % MOD;
            
            // Update sums
            sum0.put(x, (sum0.getOrDefault(x, 0L) + dp0[i]) % MOD);
            sum1.put(x, (sum1.getOrDefault(x, 0L) + dp1[i]) % MOD);
        }
        
        // Answer is sum of all partitions ending with target1 (odd number of blocks)
        long ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = (ans + dp0[i]) % MOD;
        }
        return (int) ans;
    }

    public boolean isCorrect(int[] nums, int target1, int target2, int output) {
        return alternatingXOR(nums, target1, target2) == output;
    }
}