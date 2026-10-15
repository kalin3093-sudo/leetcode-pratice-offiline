import java.util.*;
class Marker {
    public String arrangeWords(String text) {
        String[] words = text.toLowerCase().split(" ");
        Arrays.sort(words, (a, b) -> Integer.compare(a.length(), b.length()));
        String joined = String.join(" ", words);
        return Character.toUpperCase(joined.charAt(0)) + joined.substring(1);
    }

    public boolean isCorrect(String text, String output) {
        return arrangeWords(text).equals(output);
    }
}
