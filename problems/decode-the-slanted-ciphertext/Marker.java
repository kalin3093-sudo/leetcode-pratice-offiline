import java.util.*;
class Marker {
    public String decodeCiphertext(String encodedText, int rows) {
        int len = encodedText.length();
        if (len == 0) return "";
        int cols = len / rows;
        StringBuilder sb = new StringBuilder();
        for (int start = 0; start < cols; start++) {
            for (int r = 0; r < rows; r++) {
                int c = start + r;
                if (c >= cols) break;
                sb.append(encodedText.charAt(r * cols + c));
            }
        }
        String s = sb.toString();
        int end = s.length();
        while (end > 0 && s.charAt(end - 1) == ' ') end--;
        return s.substring(0, end);
    }

    public boolean isCorrect(String encodedText, int rows, String output) {
        // statement guarantees a valid encoding (length divisible by rows); malformed input has no defined answer
        if (rows <= 0 || encodedText.length() % rows != 0) return true;
        return decodeCiphertext(encodedText, rows).equals(output);
    }
}
