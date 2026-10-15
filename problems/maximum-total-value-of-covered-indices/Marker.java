import java.util.*;
class Marker {
    public long maxTotal(int[] nums, String s) {
        int n = nums.length;
        long total = 0;
        int i = 0;
        while (i < n) {
            if (s.charAt(i) == '1') {
                int start = i;
                while (i < n && s.charAt(i) == '1') i++;
                int end = i - 1;
                // Block from start to end
                // Include index start-1 if it exists
                int blockStart = (start > 0) ? start - 1 : start;
                int blockEnd = end;
                long sum = 0;
                int minVal = Integer.MAX_VALUE;
                for (int j = blockStart; j <= blockEnd; j++) {
                    sum += nums[j];
                    minVal = Math.min(minVal, nums[j]);
                }
                total += sum - minVal;
            } else {
                i++;
            }
        }
        return total;
    }

    public boolean isCorrect(int[] nums, String s, long output) {
        long expected = maxTotal(nums, s);
        return expected == output;
    }
}