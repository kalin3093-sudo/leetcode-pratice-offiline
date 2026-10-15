import java.util.*;

class Marker {
    public int minMoves(int sx, int sy, int tx, int ty) {
        if (sx > tx || sy > ty) return -1;
        int moves = 0;
        while (true) {
            if (tx == sx && ty == sy) return moves;
            if (tx < sx || ty < sy) return -1;
            
            if (tx > ty) {
                if (tx >= 2 * ty) {
                    if (tx % 2 != 0) return -1;
                    tx /= 2;
                } else {
                    tx -= ty;
                }
            } else if (ty > tx) {
                if (ty >= 2 * tx) {
                    if (ty % 2 != 0) return -1;
                    ty /= 2;
                } else {
                    ty -= tx;
                }
            } else { // tx == ty
                if (sx == 0 && ty == sy) {
                    tx = 0;
                } else if (sy == 0 && tx == sx) {
                    ty = 0;
                } else {
                    return -1;
                }
            }
            moves++;
        }
    }

    public boolean isCorrect(int sx, int sy, int tx, int ty, int output) {
        return minMoves(sx, sy, tx, ty) == output;
    }
}