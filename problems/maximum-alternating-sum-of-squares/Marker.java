import java.util.*;
class Marker {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] squares = new long[n];
        for (int i = 0; i < n; i++) {
            long x = nums[i];
            squares[i] = x * x;
        }
        Arrays.sort(squares);
        
        int pos = (n + 1) / 2;
        long posSum = 0, negSum = 0;
        for (int i = 0; i < pos; i++) {
            posSum += squares[n - 1 - i];
        }
        for (int i = 0; i < n - pos; i++) {
            negSum += squares[i];
        }
        return posSum - negSum;
    }

    public boolean isCorrect(int[] nums, long output) {
        return maxAlternatingSum(nums) == output;
    }
}