import java.util.*;
class Marker {
    public boolean canReach(int[] start, int[] target) {
        int sx = start[0], sy = start[1];
        int tx = target[0], ty = target[1];
        return ((sx + sy) & 1) == ((tx + ty) & 1);
    }

    public boolean isCorrect(int[] start, int[] target, boolean output) {
        return canReach(start, target) == output;
    }
}