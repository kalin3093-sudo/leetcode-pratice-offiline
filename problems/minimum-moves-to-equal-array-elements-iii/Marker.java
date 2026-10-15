import java.util.*;

class Marker {
    public int minMoves(int[] nums) {
        int maxVal = 0;
        for (int x : nums) maxVal = Math.max(maxVal, x);
        int moves = 0;
        for (int x : nums) {
            moves += maxVal - x;
        }
        return moves;
    }

    public boolean isCorrect(int[] nums, int output) {
        return minMoves(nums) == output;
    }
}