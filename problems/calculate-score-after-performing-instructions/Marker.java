import java.util.*;
class Marker {
    public long calculateScore(String[] instructions, int[] values) {
        int n = instructions.length;
        boolean[] vis = new boolean[n];
        long score = 0;
        int i = 0;
        while (i >= 0 && i < n && !vis[i]) {
            vis[i] = true;
            if (instructions[i].equals("add")) {
                score += values[i];
                i++;
            } else {
                i += values[i];
            }
        }
        return score;
    }

    public boolean isCorrect(String[] instructions, int[] values, long output) {
        return calculateScore(instructions, values) == output;
    }
}
