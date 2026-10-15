import java.util.*;
class Marker {
    public boolean uniformArray(int[] nums1) {
        boolean hasOdd = false, hasEven = false;
        int minOdd = Integer.MAX_VALUE, minEven = Integer.MAX_VALUE;
        for (int x : nums1) {
            if ((x & 1) == 1) {
                hasOdd = true;
                minOdd = Math.min(minOdd, x);
            } else {
                hasEven = true;
                minEven = Math.min(minEven, x);
            }
        }
        return !hasOdd || !hasEven || minOdd < minEven;
    }

    public boolean isCorrect(int[] nums1, boolean output) {
        return uniformArray(nums1) == output;
    }
}
