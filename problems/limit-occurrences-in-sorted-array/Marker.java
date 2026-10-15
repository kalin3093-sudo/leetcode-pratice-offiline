import java.util.*;
class Marker {
    public int[] limitOccurrences(int[] nums, int k) {
        List<Integer> ans = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n; ) {
            int val = nums[i];
            int j = i;
            while (j < n && nums[j] == val) j++;
            int cnt = j - i;
            int keep = Math.min(cnt, k);
            for (int t = 0; t < keep; t++) ans.add(val);
            i = j;
        }
        int[] res = new int[ans.size()];
        for (int i = 0; i < res.length; i++) res[i] = ans.get(i);
        return res;
    }

    public boolean isCorrect(int[] nums, int k, int[] output) {
        if (output == null) return false;
        int[] expected = limitOccurrences(nums, k);
        return Arrays.equals(expected, output);
    }
}