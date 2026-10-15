import java.util.*;
class Marker {
    public long lastInteger(long n) {
        long head = 1;
        long step = 1;
        long remaining = n;
        boolean left = true;
        while (remaining > 1) {
            if (left) {
                // Left to right: head stays, remove every 2nd from left
                // remaining = ceil(remaining / 2)
            } else {
                // Right to left: if remaining is odd, head stays; if even, head += step
                if (remaining % 2 == 1) {
                    // head stays
                } else {
                    head += step;
                }
            }
            remaining = (remaining + 1) / 2; // ceil
            step *= 2;
            left = !left;
        }
        return head;
    }

    public boolean isCorrect(long n, long output) {
        return output == lastInteger(n);
    }
}