import java.util.*;
class Marker {
    public int longestAlternating(int[] nums) {
        int n = nums.length;
        if (n == 1) return 1;
        if (n == 2) return nums[0] == nums[1] ? 1 : 2;
        
        // left[i][0]: max length of alternating subarray ending at i with last comparison '<'
        // left[i][1]: max length ending at i with last comparison '>'
        int[][] left = new int[n][2];
        int[][] right = new int[n][2];
        
        // Initialize: single element has length 1
        left[0][0] = left[0][1] = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i - 1] < nums[i]) {
                left[i][0] = left[i - 1][1] + 1;
            } else if (nums[i - 1] > nums[i]) {
                left[i][1] = left[i - 1][0] + 1;
            }
            // Single element at i
            left[i][0] = Math.max(left[i][0], 1);
            left[i][1] = Math.max(left[i][1], 1);
        }
        
        right[n - 1][0] = right[n - 1][1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                right[i][0] = right[i + 1][1] + 1;
            } else if (nums[i] > nums[i + 1]) {
                right[i][1] = right[i + 1][0] + 1;
            }
            right[i][0] = Math.max(right[i][0], 1);
            right[i][1] = Math.max(right[i][1], 1);
        }
        
        int ans = 1;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, left[i][0]);
            ans = Math.max(ans, left[i][1]);
        }
        
        // Try removing each element
        for (int r = 1; r < n - 1; r++) {
            int leftVal = nums[r - 1];
            int rightVal = nums[r + 1];
            
            if (leftVal < rightVal) {
                // Pattern: ... > leftVal < rightVal > ...
                int bestLeft = left[r - 1][1];
                int bestRight = right[r + 1][1];
                if (bestLeft > 0 && bestRight > 0) {
                    ans = Math.max(ans, bestLeft + bestRight);
                }
            } else if (leftVal > rightVal) {
                // Pattern: ... < leftVal > rightVal < ...
                int bestLeft = left[r - 1][0];
                int bestRight = right[r + 1][0];
                if (bestLeft > 0 && bestRight > 0) {
                    ans = Math.max(ans, bestLeft + bestRight);
                }
            }
            // if equal, cannot connect
        }
        
        return ans;
    }

    public boolean isCorrect(int[] nums, int output) {
        return output == longestAlternating(nums);
    }
}