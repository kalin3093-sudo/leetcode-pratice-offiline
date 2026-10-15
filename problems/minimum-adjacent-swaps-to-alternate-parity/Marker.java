import java.util.*;
class Marker {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        int evenCnt = 0;
        for (int x : nums) if ((x & 1) == 0) evenCnt++;
        int oddCnt = n - evenCnt;
        if (Math.abs(evenCnt - oddCnt) > 1) return -1;

        List<Integer> evenIdx = new ArrayList<>();
        for (int i = 0; i < n; i++) if ((nums[i] & 1) == 0) evenIdx.add(i);

        int ans = Integer.MAX_VALUE;
        if (n % 2 == 0) {
            ans = Math.min(ans, calc(evenIdx, 0, n));
            ans = Math.min(ans, calc(evenIdx, 1, n));
        } else {
            if (evenCnt > oddCnt) ans = Math.min(ans, calc(evenIdx, 0, n));
            else ans = Math.min(ans, calc(evenIdx, 1, n));
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private int calc(List<Integer> evenIdx, int startParity, int n) {
        int cost = 0;
        int ptr = 0;
        for (int i = startParity; i < n; i += 2) {
            cost += Math.abs(evenIdx.get(ptr++) - i);
        }
        return cost;
    }

    public boolean isCorrect(int[] nums, int output) {
        int expected = minSwaps(nums);
        return expected == output;
    }
}