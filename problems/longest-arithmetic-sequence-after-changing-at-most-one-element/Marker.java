import java.util.*;
class Marker {
    public int longestArithmetic(int[] nums) {
        int n = nums.length;
        int[] L = new int[n];
        int[] R = new int[n];
        int[] diffL = new int[n];
        int[] diffR = new int[n];
        
        L[0] = 1;
        for (int i = 1; i < n; i++) {
            if (i == 1) {
                L[i] = 2;
                diffL[i] = nums[i] - nums[i - 1];
            } else {
                int d = nums[i] - nums[i - 1];
                if (d == diffL[i - 1]) {
                    L[i] = L[i - 1] + 1;
                    diffL[i] = d;
                } else {
                    L[i] = 2;
                    diffL[i] = d;
                }
            }
        }
        
        R[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (i == n - 2) {
                R[i] = 2;
                diffR[i] = nums[i + 1] - nums[i];
            } else {
                int d = nums[i + 1] - nums[i];
                if (d == diffR[i + 1]) {
                    R[i] = R[i + 1] + 1;
                    diffR[i] = d;
                } else {
                    R[i] = 2;
                    diffR[i] = d;
                }
            }
        }
        
        int ans = 1;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, L[i]);
            ans = Math.max(ans, R[i]);
        }
        
        // Try changing each element
        for (int i = 1; i < n - 1; i++) {
            // Try connecting left and right
            long sum = (long) nums[i - 1] + nums[i + 1];
            if (sum % 2 == 0) {
                int d = (int)(sum / 2 - nums[i - 1]); // (nums[i+1] - nums[i-1]) / 2
                if (L[i - 1] >= 2 && diffL[i - 1] == d) {
                    ans = Math.max(ans, L[i - 1] + R[i + 1] + 1);
                } else if (L[i - 1] == 1) {
                    ans = Math.max(ans, R[i + 1] + 1);
                }
                if (R[i + 1] >= 2 && diffR[i + 1] == d) {
                    ans = Math.max(ans, R[i + 1] + L[i - 1] + 1);
                } else if (R[i + 1] == 1) {
                    ans = Math.max(ans, L[i - 1] + 1);
                }
            }
            // Try extending only left (change nums[i] to continue left's arithmetic sequence)
            if (i >= 2 && L[i - 1] >= 2) {
                ans = Math.max(ans, L[i - 1] + 1);
            }
            // Try extending only right
            if (i + 2 < n && R[i + 1] >= 2) {
                ans = Math.max(ans, R[i + 1] + 1);
            }
        }
        
        // Edge cases: change first or last element
        if (n >= 2) {
            ans = Math.max(ans, R[1] + 1);
            ans = Math.max(ans, L[n - 2] + 1);
        }
        
        return ans;
    }

    public boolean isCorrect(int[] nums, int output) {
        return output == longestArithmetic(nums);
    }
}