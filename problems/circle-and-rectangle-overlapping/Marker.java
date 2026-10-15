import java.util.*;
class Marker {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        int loX = Math.min(x1, x2), hiX = Math.max(x1, x2);
        int loY = Math.min(y1, y2), hiY = Math.max(y1, y2);
        int dx = Math.max(0, Math.max(loX - xCenter, xCenter - hiX));
        int dy = Math.max(0, Math.max(loY - yCenter, yCenter - hiY));
        return (long) dx * dx + (long) dy * dy <= (long) radius * radius;
    }

    public boolean isCorrect(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2, boolean output) {
        return checkOverlap(radius, xCenter, yCenter, x1, y1, x2, y2) == output;
    }
}
