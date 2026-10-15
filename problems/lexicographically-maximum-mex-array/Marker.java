import java.util.*;
class Marker {
    public int[] maximumMEX(int[] nums) {
        int n = nums.length;
        int[] freq = new int[n + 2]; // freq of values in remaining suffix
        for (int x : nums) if (x <= n + 1) freq[x]++;
        
        List<Integer> result = new ArrayList<>();
        int i = 0;
        while (i < n) {
            // Find current suffix MEX
            int mex = 0;
            while (freq[mex] > 0) mex++;
            
            if (mex == 0) {
                // No 0 in remaining, take one element
                if (nums[i] <= n + 1) freq[nums[i]]--;
                result.add(0);
                i++;
            } else {
                // Need shortest prefix containing 0..mex-1
                int[] seen = new int[mex];
                int count = 0;
                int j = i;
                while (count < mex && j < n) {
                    int x = nums[j];
                    if (x < mex && seen[x] == 0) {
                        seen[x] = 1;
                        count++;
                    }
                    if (x <= n + 1) freq[x]--;
                    j++;
                }
                result.add(mex);
                i = j;
            }
        }
        int[] ans = new int[result.size()];
        for (int k = 0; k < ans.length; k++) ans[k] = result.get(k);
        return ans;
    }

    public boolean isCorrect(int[] nums, int[] output) {
        if (output == null) return false;
        int[] expected = maximumMEX(nums);
        return Arrays.equals(expected, output);
    }
}