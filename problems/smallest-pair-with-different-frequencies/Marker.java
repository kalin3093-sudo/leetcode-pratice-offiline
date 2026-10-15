import java.util.*;

class Marker {
    public int[] minDistinctFreqPair(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int v : nums)
            freq.merge(v, 1, Integer::sum);
        List<Integer> keys = new ArrayList<>(freq.keySet());
        Collections.sort(keys);
        int f0 = freq.get(keys.get(0));
        for (int i = 1; i < keys.size(); i++)
            if (freq.get(keys.get(i)) != f0)
                return new int[] { keys.get(0), keys.get(i) };
        return new int[] { -1, -1 };
    }

    public boolean isCorrect(int[] nums, int[] output) {
        int[] expected = minDistinctFreqPair(nums);
        if (output == null || output.length != expected.length)
            return false;
        for (int i = 0; i < expected.length; i++)
            if (expected[i] != output[i])
                return false;
        return true;
    }
}
