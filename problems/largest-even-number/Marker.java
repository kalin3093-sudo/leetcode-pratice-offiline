import java.util.*;
class Marker {
    public String largestEven(String s) {
        int lastTwo = s.lastIndexOf('2');
        if (lastTwo == -1) return "";
        return s.substring(0, lastTwo + 1);
    }

    public boolean isCorrect(String s, String output) {
        return output.equals(largestEven(s));
    }
}