import java.util.*;
class Marker {
    public int minStable(int[] nums, int maxC) {
        int n = nums.length;
        if (n == 0) return 0;
        
        // Binary search on the answer (stability factor = max stable subarray length)
        int left = 0, right = n;
        while (left < right) {
            int mid = (left + right) / 2;
            if (canAchieve(nums, maxC, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    
    // Check if we can make all subarrays of length > k have GCD = 1
    // i.e., no subarray of length k+1 has GCD >= 2
    // Equivalently: can we hit every window of size k+1 with an edit if its GCD > 1?
    private boolean canAchieve(int[] nums, int maxC, int k) {
        int n = nums.length;
        if (k == 0) {
            // No stable subarray allowed, need to edit all elements >= 2
            int edits = 0;
            for (int x : nums) if (x >= 2) edits++;
            return edits <= maxC;
        }
        
        // Build sparse table for range GCD queries
        int logN = 32 - Integer.numberOfLeadingZeros(n);
        int[][] st = new int[logN][n];
        for (int i = 0; i < n; i++) st[0][i] = nums[i];
        for (int j = 1; j < logN; j++) {
            for (int i = 0; i + (1 << j) <= n; i++) {
                st[j][i] = gcd(st[j-1][i], st[j-1][i + (1 << (j-1))]);
            }
        }
        
        // Precompute log2 for lengths up to n
        int[] log2 = new int[n + 1];
        for (int i = 2; i <= n; i++) log2[i] = log2[i/2] + 1;
        
        // Greedy: find leftmost window of size k+1 with GCD > 1, edit its rightmost element
        int edits = 0;
        int lastEdit = -1; // last position we edited
        
        for (int i = 0; i + k < n; i++) {
            // If we already edited an element in this window, its GCD becomes 1
            if (lastEdit >= i) continue;
            
            int g = rangeGcd(st, log2, i, i + k);
            if (g >= 2) {
                edits++;
                if (edits > maxC) return false;
                lastEdit = i + k; // edit the rightmost element of this window
            }
        }
        return true;
    }
    
    private int rangeGcd(int[][] st, int[] log2, int l, int r) {
        // GCD of [l, r] inclusive
        int len = r - l + 1;
        int j = log2[len];
        if ((1 << j) == len) return st[j][l];
        return gcd(st[j][l], st[j][r - (1 << j) + 1]);
    }
    
    private int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    public boolean isCorrect(int[] nums, int maxC, int output) {
        return minStable(nums, maxC) == output;
    }
}