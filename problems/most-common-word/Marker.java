import java.util.*;
class Marker {
    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> bannedSet = new HashSet<>(Arrays.asList(banned));
        Map<String, Integer> freq = new HashMap<>();
        
        StringBuilder word = new StringBuilder();
        for (char c : paragraph.toCharArray()) {
            if (Character.isLetter(c)) {
                word.append(Character.toLowerCase(c));
            } else {
                if (word.length() > 0) {
                    String w = word.toString();
                    if (!bannedSet.contains(w)) {
                        freq.merge(w, 1, Integer::sum);
                    }
                    word.setLength(0);
                }
            }
        }
        if (word.length() > 0) {
            String w = word.toString();
            if (!bannedSet.contains(w)) {
                freq.merge(w, 1, Integer::sum);
            }
        }
        
        String best = "";
        int maxFreq = 0;
        for (Map.Entry<String, Integer> e : freq.entrySet()) {
            if (e.getValue() > maxFreq) {
                maxFreq = e.getValue();
                best = e.getKey();
            }
        }
        return best;
    }

    public boolean isCorrect(String paragraph, String[] banned, String output) {
        return mostCommonWord(paragraph, banned).equals(output);
    }
}