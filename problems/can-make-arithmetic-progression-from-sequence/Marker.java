import java.util.*;
class Marker {
    public boolean canMakeArithmeticProgression(int[] arr) {
        int[] a = arr.clone();
        Arrays.sort(a);
        int d = a[1] - a[0];
        for (int i = 2; i < a.length; i++) if (a[i] - a[i - 1] != d) return false;
        return true;
    }

    public boolean isCorrect(int[] arr, boolean output) {
        return canMakeArithmeticProgression(arr) == output;
    }
}
