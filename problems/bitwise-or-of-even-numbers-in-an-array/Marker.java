import java.util.*;
class Marker {
    public int evenNumberBitwiseORs(int[] nums) {
        int or = 0;
        for (int x : nums) if ((x & 1) == 0) or |= x;
        return or;
    }

    public boolean isCorrect(int[] nums, int output) {
        return evenNumberBitwiseORs(nums) == output;
    }
}
