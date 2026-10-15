import java.util.*;

class Marker {
    public long shadowPairs(int[] nums) {
        int n = nums.length;
        List<Integer> stack = new ArrayList<>();
        Map<Integer, Integer> count = new HashMap<>();
        long ans = 0;
        for (int x : nums) {
            while (!stack.isEmpty() && stack.get(stack.size() - 1) > x) {
                int v = stack.remove(stack.size() - 1);
                int c = count.get(v);
                count.put(v, c - 1);
                if (c == 1) count.remove(v);
            }
            int smaller = 0;
            for (Map.Entry<Integer, Integer> e : count.entrySet()) {
                if (e.getKey() < x) smaller += e.getValue();
            }
            ans += smaller;
            stack.add(x);
            count.put(x, count.getOrDefault(x, 0) + 1);
        }
        return ans;
    }

    public boolean isCorrect(int[] nums, long output) {
        return output == shadowPairs(nums);
    }
}