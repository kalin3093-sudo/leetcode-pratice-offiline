import java.util.*;

class Marker {
    public String mapWordWeights(String[] words, int[] weights) {
        StringBuilder sb = new StringBuilder();
        for (String w : words) {
            int sum = 0;
            for (char c : w.toCharArray()) sum += weights[c - 'a'];
            sb.append((char) ('z' - (sum % 26)));
        }
        return sb.toString();
    }

    public boolean isCorrect(String[] words, int[] weights, String output) {
        return mapWordWeights(words, weights).equals(output);
    }
}
