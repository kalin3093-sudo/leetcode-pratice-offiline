import java.util.*;
class Marker {
    public String strWithout3a3b(int a, int b) {
        StringBuilder sb = new StringBuilder();
        while (a + b > 0) {
            int n = sb.length();
            if (n >= 2 && sb.charAt(n - 1) == sb.charAt(n - 2)) {
                if (sb.charAt(n - 1) == 'b') {
                    sb.append('a');
                    a--;
                } else {
                    sb.append('b');
                    b--;
                }
            } else {
                if (a >= b) {
                    sb.append('a');
                    a--;
                } else {
                    sb.append('b');
                    b--;
                }
            }
        }
        return sb.toString();
    }
    public boolean isCorrect(int a, int b, String output) {
        if (output == null || output.length() != a + b) return false;
        int ca = 0, cb = 0;
        for (int i = 0; i < output.length(); i++) {
            char c = output.charAt(i);
            if (c == 'a') ca++;
            else if (c == 'b') cb++;
            else return false;
            if (i >= 2 && output.charAt(i) == output.charAt(i - 1) && output.charAt(i) == output.charAt(i - 2)) {
                return false;
            }
        }
        return ca == a && cb == b;
    }
}
