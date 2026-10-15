import java.util.*;

class Marker {
    public int maxDistance(String moves) {
        int x = 0, y = 0, underscores = 0;
        for (char c : moves.toCharArray()) {
            if (c == 'U') y++;
            else if (c == 'D') y--;
            else if (c == 'L') x--;
            else if (c == 'R') x++;
            else underscores++;
        }
        return Math.abs(x) + Math.abs(y) + underscores;
    }

    public boolean isCorrect(String moves, int output) {
        return output == maxDistance(moves);
    }
}