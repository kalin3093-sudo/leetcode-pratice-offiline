import java.util.*;

class Marker {
    public boolean canTransform(int[] source, int[] target) {
        long s = 0, t = 0;
        for (int i = 0; i < source.length; i++) {
            s += source[i];
            t += target[i];
        }
        return s == t;
    }

    public boolean isCorrect(int[] source, int[] target, boolean output) {
        return output == canTransform(source, target);
    }
}
