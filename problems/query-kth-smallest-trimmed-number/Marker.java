import java.util.*;
class Marker {
    public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
        int[] ans = new int[queries.length];
        for (int q = 0; q < queries.length; q++) {
            int k = queries[q][0], trim = queries[q][1];
            int[] idx = new int[nums.length];
            for (int i = 0; i < nums.length; i++) idx[i] = i;
            final int t = trim;
            Integer[] I = new Integer[nums.length];
            for (int i = 0; i < nums.length; i++) I[i] = i;
            Arrays.sort(I, (a, b) -> {
                String sa = nums[a].substring(nums[a].length() - t);
                String sb = nums[b].substring(nums[b].length() - t);
                int cmp = sa.compareTo(sb);
                return cmp != 0 ? cmp : a - b;
            });
            ans[q] = I[k - 1];
        }
        return ans;
    }

    public boolean isCorrect(String[] nums, int[][] queries, int[] output) {
        return Arrays.equals(smallestTrimmedNumbers(nums, queries), output);
    }
}
