import java.util.*;
class Marker {
    public int maxValidSplits(int[] nums) {
        int n = nums.length;
        int best = 0;
        
        // Try removing no element
        best = Math.max(best, countValidSplits(nums, -1));
        
        // Try removing each element
        for (int remove = 0; remove < n; remove++) {
            best = Math.max(best, countValidSplits(nums, remove));
        }
        
        return best;
    }
    
    private int countValidSplits(int[] nums, int remove) {
        int n = nums.length;
        int m = n - (remove >= 0 ? 1 : 0);
        if (m <= 1) return 0;
        
        int[] arr = new int[m];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (i != remove) arr[idx++] = nums[i];
        }
        
        int[] preGcd = new int[m];
        int[] sufGcd = new int[m];
        
        preGcd[0] = arr[0];
        for (int i = 1; i < m; i++) {
            preGcd[i] = gcd(preGcd[i - 1], arr[i]);
        }
        
        sufGcd[m - 1] = arr[m - 1];
        for (int i = m - 2; i >= 0; i--) {
            sufGcd[i] = gcd(sufGcd[i + 1], arr[i]);
        }
        
        int count = 0;
        for (int i = 0; i < m - 1; i++) {
            if (preGcd[i] == sufGcd[i + 1]) count++;
        }
        return count;
    }
    
    private int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    public boolean isCorrect(int[] nums, int output) {
        int expected = maxValidSplits(nums);
        return expected == output;
    }
}