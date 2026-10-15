import java.util.*;
class Marker {
    public int[] maxKDistinct(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int x : nums) set.add(x);
        
        List<Integer> list = new ArrayList<>(set);
        list.sort(Collections.reverseOrder());
        
        int m = Math.min(k, list.size());
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    public boolean isCorrect(int[] nums, int k, int[] output) {
        return Arrays.equals(maxKDistinct(nums, k), output);
    }
}