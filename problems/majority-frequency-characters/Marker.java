import java.util.*;
class Marker {
    public String majorityFrequencyGroup(String s) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        
        Map<Integer, List<Character>> groups = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0) {
                groups.computeIfAbsent(freq[i], k -> new ArrayList<>()).add((char) ('a' + i));
            }
        }
        
        int bestFreq = -1;
        int bestSize = -1;
        for (Map.Entry<Integer, List<Character>> e : groups.entrySet()) {
            int f = e.getKey();
            int size = e.getValue().size();
            if (size > bestSize || (size == bestSize && f > bestFreq)) {
                bestSize = size;
                bestFreq = f;
            }
        }
        
        List<Character> best = groups.get(bestFreq);
        StringBuilder sb = new StringBuilder();
        for (char c : best) sb.append(c);
        return sb.toString();
    }

    public boolean isCorrect(String s, String output) {
        return majorityFrequencyGroup(s).equals(output);
    }
}