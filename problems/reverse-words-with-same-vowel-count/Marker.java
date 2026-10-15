import java.util.*;

class Marker {
    static int vowelCount(String w) {
        int c = 0;
        for (char ch : w.toCharArray())
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u')
                c++;
        return c;
    }

    public String reverseWords(String s) {
        String[] words = s.split(" ");
        int target = vowelCount(words[0]);
        for (int i = 1; i < words.length; i++) {
            if (vowelCount(words[i]) == target)
                words[i] = new StringBuilder(words[i]).reverse().toString();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            if (i > 0) sb.append(' ');
            sb.append(words[i]);
        }
        return sb.toString();
    }

    public boolean isCorrect(String s, String output) {
        return reverseWords(s).equals(output);
    }
}
