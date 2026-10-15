import java.util.*;
class Marker {
    public int[] minOperations(int[] nums) {
        ArrayList<Integer> pals = new ArrayList<>();
        for (int i = 1; i <= 20000; i++) {
            String b = Integer.toBinaryString(i);
            if (new StringBuilder(b).reverse().toString().equals(b)) pals.add(i);
        }
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int best = Integer.MAX_VALUE;
            for (int p : pals) best = Math.min(best, Math.abs(nums[i] - p));
            ans[i] = best;
        }
        return ans;
    }

    public boolean isCorrect(int[] nums, int[] output) {
        if (output == null || output.length != nums.length) return false;
        int[] exp = minOperations(nums);
        for (int i = 0; i < nums.length; i++) if (output[i] != exp[i]) return false;
        return true;
    }
}
