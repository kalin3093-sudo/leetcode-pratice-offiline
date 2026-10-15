import java.util.*;

class Marker {
    public int[] countWordOccurrences(String[] chunks, String[] queries) {
        StringBuilder sb = new StringBuilder();
        for (String chunk : chunks) sb.append(chunk);
        String s = sb.toString();
        int n = s.length();

        Map<String, Integer> wordCount = new HashMap<>();
        StringBuilder word = new StringBuilder();
        
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'z') {
                word.append(c);
            } else if (c == '-') {
                // Check if joiner hyphen
                boolean isJoiner = (i > 0 && i + 1 < n);
                if (isJoiner) {
                    char prev = s.charAt(i - 1);
                    char next = s.charAt(i + 1);
                    isJoiner = (prev >= 'a' && prev <= 'z' && next >= 'a' && next <= 'z');
                }
                if (isJoiner) {
                    word.append(c);
                } else {
                    if (word.length() > 0) {
                        String w = word.toString();
                        wordCount.put(w, wordCount.getOrDefault(w, 0) + 1);
                        word.setLength(0);
                    }
                }
            } else {
                if (word.length() > 0) {
                    String w = word.toString();
                    wordCount.put(w, wordCount.getOrDefault(w, 0) + 1);
                    word.setLength(0);
                }
            }
        }
        if (word.length() > 0) {
            String w = word.toString();
            wordCount.put(w, wordCount.getOrDefault(w, 0) + 1);
        }

        int[] ans = new int[queries.length];
        for (int j = 0; j < queries.length; j++) {
            ans[j] = wordCount.getOrDefault(queries[j], 0);
        }
        return ans;
    }

    public boolean isCorrect(String[] chunks, String[] queries, int[] output) {
        return Arrays.equals(output, countWordOccurrences(chunks, queries));
    }
}