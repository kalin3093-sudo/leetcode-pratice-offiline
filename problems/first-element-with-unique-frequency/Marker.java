import java.util.*;
class Marker {
    public int firstUniqueFreq(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int x : nums) freq.merge(x, 1, Integer::sum);
        Map<Integer, Integer> freqCount = new HashMap<>();
        for (int f : freq.values()) freqCount.merge(f, 1, Integer::sum);
        for (int x : nums) {
            if (freqCount.get(freq.get(x)) == 1) return x;
        }
        return -1;
    }
    public boolean isCorrect(int[] nums, int output) {
        return firstUniqueFreq(nums) == output;
    }
}