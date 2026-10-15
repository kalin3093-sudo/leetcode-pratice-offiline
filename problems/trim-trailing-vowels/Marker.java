import java.util.*;

class Marker {
    public String trimTrailingVowels(String s) {
        int i = s.length();
        while (i > 0 && isVowel(s.charAt(i - 1))) i--;
        return s.substring(0, i);
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    public boolean isCorrect(String s, String output) {
        return output != null && output.equals(trimTrailingVowels(s));
    }
}
