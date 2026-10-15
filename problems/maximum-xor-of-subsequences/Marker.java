import java.util.*;
class Marker {
    public int maxXorSubsequences(int[] nums) {
        // Build XOR basis
        int[] basis = new int[31]; // up to 30 bits for 10^9
        for (int x : nums) {
            int val = x;
            for (int i = 30; i >= 0; i--) {
                if ((val & (1 << i)) == 0) continue;
                if (basis[i] == 0) {
                    basis[i] = val;
                    break;
                }
                val ^= basis[i];
            }
        }
        
        // Find maximum XOR
        int res = 0;
        for (int i = 30; i >= 0; i--) {
            if ((res ^ basis[i]) > res) {
                res ^= basis[i];
            }
        }
        return res;
    }

    public boolean isCorrect(int[] nums, int output) {
        int expected = maxXorSubsequences(nums);
        return expected == output;
    }
}