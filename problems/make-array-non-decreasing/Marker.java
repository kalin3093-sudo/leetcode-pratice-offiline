import java.util.*;
class Marker {
    public int maximumPossibleSize(int[] nums) {
        int n = nums.length;
        if (n <= 1) return n;
        
        int[] stack = new int[n];
        int top = 0;
        
        for (int i = n - 1; i >= 0; i--) {
            while (top > 0 && stack[top - 1] < nums[i]) {
                top--;
            }
            stack[top++] = nums[i];
        }
        return top;
    }

    public boolean isCorrect(int[] nums, int output) {
        return maximumPossibleSize(nums) == output;
    }
}