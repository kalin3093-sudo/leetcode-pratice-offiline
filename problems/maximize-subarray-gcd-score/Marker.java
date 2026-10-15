import java.util.*;
class Marker {
    public long maxGCDScore(int[] nums, int k) {
        int n = nums.length;
        long ans = 0;
        
        for (int i = 0; i < n; i++) {
            int[] oddParts = new int[n - i];
            int[] pow2 = new int[n - i];
            int idx = 0;
            
            for (int j = i; j < n; j++) {
                int x = nums[j];
                int p = 0;
                while ((x & 1) == 0) {
                    x >>= 1;
                    p++;
                }
                oddParts[idx] = x;
                pow2[idx] = p;
                idx++;
                
                int gOdd = oddParts[0];
                for (int t = 1; t < idx; t++) {
                    gOdd = gcd(gOdd, oddParts[t]);
                }
                
                int len = idx;
                int[] pCopy = Arrays.copyOf(pow2, idx);
                Arrays.sort(pCopy);
                
                int newMinP;
                if (k >= len) {
                    newMinP = pCopy[0] + 1;
                } else {
                    newMinP = Math.min(pCopy[0] + 1, pCopy[k]);
                }
                
                long score = (long) len * gOdd * (1L << newMinP);
                ans = Math.max(ans, score);
            }
        }
        return ans;
    }
    
    private int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    public boolean isCorrect(int[] nums, int k, long output) {
        return maxGCDScore(nums, k) == output;
    }
}