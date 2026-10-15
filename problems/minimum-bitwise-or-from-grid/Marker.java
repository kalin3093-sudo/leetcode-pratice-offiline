import java.util.*;
class Marker {
    public int minimumOR(int[][] grid) {
        int ans = 0;
        for (int bit = 17; bit >= 0; bit--) {
            // can bit stay unset? allowed bits = forced high bits (ans) + all lower bits
            int allowed = ans | ((1 << bit) - 1);
            boolean ok = true;
            for (int[] row : grid) {
                boolean found = false;
                for (int val : row) {
                    if ((val & ~allowed) == 0) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    ok = false;
                    break;
                }
            }
            if (!ok) {
                ans |= (1 << bit);
            }
        }
        return ans;
    }

    public boolean isCorrect(int[][] grid, int output) {
        int expected = minimumOR(grid);
        return expected == output;
    }
}