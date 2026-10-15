import java.util.*;
class Marker {
    public String rearrangeString(String s, char x, char y) {
        StringBuilder ys = new StringBuilder(), xs = new StringBuilder(), rest = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == y) ys.append(c);
            else if (c == x) xs.append(c);
            else rest.append(c);
        }
        return ys.toString() + rest + xs.toString();
    }

    public boolean isCorrect(String s, char x, char y, String output) {
        if (output == null) return false;
        char[] a = s.toCharArray(), b = output.toCharArray();
        Arrays.sort(a); Arrays.sort(b);
        if (!Arrays.equals(a, b)) return false;
        int firstX = output.indexOf(x);
        int lastY = output.lastIndexOf(y);
        if (firstX < 0 || lastY < 0) return true;
        return lastY < firstX;
    }
}
