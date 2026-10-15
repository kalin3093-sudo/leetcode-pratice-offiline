import java.util.*;
class Marker {
    public int isPrefixOfWord(String sentence, String searchWord) {
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            if (words[i].startsWith(searchWord)) return i + 1;
        }
        return -1;
    }

    public boolean isCorrect(String sentence, String searchWord, int output) {
        return isPrefixOfWord(sentence, searchWord) == output;
    }
}
