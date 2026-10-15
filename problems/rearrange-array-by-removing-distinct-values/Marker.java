import java.util.*;
class Marker {
    public int[] rearrangeArray(int[] nums) {
        List<Integer> rem = new ArrayList<>();
        for (int v : nums) rem.add(v);
        List<Integer> ans = new ArrayList<>();
        while (!rem.isEmpty()) {
            TreeSet<Integer> distinct = new TreeSet<>(rem);
            for (int v : distinct) {
                ans.add(v);
                rem.remove(Integer.valueOf(v));
            }
        }
        int[] r = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) r[i] = ans.get(i);
        return r;
    }

    public boolean isCorrect(int[] nums, int[] output) {
        return Arrays.equals(rearrangeArray(nums), output);
    }
}
