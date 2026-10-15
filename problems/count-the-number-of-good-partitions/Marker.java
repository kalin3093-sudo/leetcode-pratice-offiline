import java.util.*;

class Marker {
    private static final int MOD = 1_000_000_007;

    public int numberOfGoodPartitions(int[] nums) {
        int n = nums.length;
        Map<Integer, int[]> ranges = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            if (!ranges.containsKey(x)) {
                ranges.put(x, new int[]{i, i});
            } else {
                ranges.get(x)[1] = i;
            }
        }

        List<int[]> segments = new ArrayList<>(ranges.values());
        segments.sort(Comparator.comparingInt(a -> a[0]));

        int merged = 0;
        int currL = segments.get(0)[0];
        int currR = segments.get(0)[1];
        for (int i = 1; i < segments.size(); i++) {
            if (segments.get(i)[0] <= currR) {
                currR = Math.max(currR, segments.get(i)[1]);
            } else {
                merged++;
                currL = segments.get(i)[0];
                currR = segments.get(i)[1];
            }
        }
        merged++;

        long ans = 1;
        for (int i = 0; i < merged - 1; i++) {
            ans = ans * 2 % MOD;
        }
        return (int) ans;
    }

    public boolean isCorrect(int[] nums, int output) {
        return output == numberOfGoodPartitions(nums);
    }
}