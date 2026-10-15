import java.util.*;

class Marker {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        int bound = 1;
        while (bound <= 2000) bound *= 2;
        boolean[] pair = new boolean[bound];
        for (int i = 0; i < n; i++)
            for (int j = i; j < n; j++)
                pair[nums[i] ^ nums[j]] = true;
        boolean[] res = new boolean[bound];
        for (int i = 0; i < n; i++)
            for (int v = 0; v < bound; v++)
                if (pair[v]) res[nums[i] ^ v] = true;
        int c = 0;
        for (boolean b : res)
            if (b) c++;
        return c;
    }

    public boolean isCorrect(int[] nums, int output) {
        return output == uniqueXorTriplets(nums);
    }
}
