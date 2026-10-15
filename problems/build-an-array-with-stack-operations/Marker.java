import java.util.*;
class Marker {
    public List<String> buildArray(int[] target, int n) {
        List<String> ops = new ArrayList<>();
        int ptr = 0;
        for (int i = 1; i <= n && ptr < target.length; i++) {
            ops.add("Push");
            if (target[ptr] == i) ptr++;
            else ops.add("Pop");
        }
        return ops;
    }

    public boolean isCorrect(int[] target, int n, List<String> output) {
        if (output == null) return false;
        return buildArray(target, n).equals(output);
    }
}
