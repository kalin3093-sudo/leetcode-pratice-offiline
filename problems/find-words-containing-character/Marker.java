import java.util.*;
class Marker {
    public List<Integer> findWordsContaining(String[] words, char x) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].indexOf(x) >= 0) res.add(i);
        }
        return res;
    }

    public boolean isCorrect(String[] words, char x, List<Integer> output) {
        return findWordsContaining(words, x).equals(output);
    }
}