import java.util.*;

class Marker {
    public int countSpecialIntegers(int[] nums) {
        Map<Integer, List<Integer>> pos = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            pos.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        int ans = 0;
        for (List<Integer> p : pos.values()) {
            if (p.size() >= 3) {
                int gap = p.get(1) - p.get(0);
                boolean ok = true;
                for (int i = 2; i < p.size(); i++) {
                    if (p.get(i) - p.get(i - 1) != gap) {
                        ok = false;
                        break;
                    }
                }
                if (ok) ans++;
            }
        }
        return ans;
    }

    public boolean isCorrect(int[] nums, int output) {
        return output == countSpecialIntegers(nums);
    }
}