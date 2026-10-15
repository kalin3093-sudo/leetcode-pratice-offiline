import java.util.*;
class Marker {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;
        
        Arrays.sort(idx, (a, b) -> {
            int sa = digitSum(nums[a]);
            int sb = digitSum(nums[b]);
            if (sa != sb) return Integer.compare(sa, sb);
            return Integer.compare(nums[a], nums[b]);
        });
        
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) pos[idx[i]] = i;
        
        boolean[] visited = new boolean[n];
        int cycles = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int j = i;
                while (!visited[j]) {
                    visited[j] = true;
                    j = pos[j];
                }
                cycles++;
            }
        }
        return n - cycles;
    }
    
    private int digitSum(int x) {
        int sum = 0;
        while (x > 0) {
            sum += x % 10;
            x /= 10;
        }
        return sum;
    }

    public boolean isCorrect(int[] nums, int output) {
        return minSwaps(nums) == output;
    }
}