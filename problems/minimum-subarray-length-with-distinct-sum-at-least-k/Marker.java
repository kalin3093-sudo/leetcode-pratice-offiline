import java.util.*;
class Marker {
    public int minLength(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> freq = new HashMap<>();
        int uniqueSum = 0;
        int left = 0;
        int minLen = n + 1;
        
        for (int right = 0; right < n; right++) {
            int x = nums[right];
            int oldFreq = freq.getOrDefault(x, 0);
            freq.put(x, oldFreq + 1);
            
            if (oldFreq == 0) {
                uniqueSum += x;
            } else if (oldFreq == 1) {
                uniqueSum -= x;
            }
            
            while (left <= right && uniqueSum >= k) {
                minLen = Math.min(minLen, right - left + 1);
                int y = nums[left];
                int oldFreqY = freq.get(y);
                if (oldFreqY == 1) {
                    uniqueSum -= y;
                } else if (oldFreqY == 2) {
                    uniqueSum += y;
                }
                freq.put(y, oldFreqY - 1);
                if (freq.get(y) == 0) freq.remove(y);
                left++;
            }
        }
        
        return minLen == n + 1 ? -1 : minLen;
    }

    public boolean isCorrect(int[] nums, int k, int output) {
        return minLength(nums, k) == output;
    }
}