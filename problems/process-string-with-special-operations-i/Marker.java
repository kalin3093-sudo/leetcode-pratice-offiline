import java.util.*;
class Marker {
    public String processStr(String s) {
        StringBuilder sb = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (ch == '*') { if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1); }
            else if (ch == '#') sb.append(sb);
            else if (ch == '%') sb.reverse();
            else sb.append(ch);
        }
        return sb.toString();
    }

    public boolean isCorrect(String s, String output) {
        return processStr(s).equals(output);
    }
}
