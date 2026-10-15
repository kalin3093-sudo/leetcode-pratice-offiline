import java.util.*;
class Marker {
    public String findCommonResponse(List<List<String>> responses) {
        Map<String, Integer> freq = new HashMap<>();
        for (List<String> day : responses) {
            Set<String> seen = new HashSet<>(day);
            for (String s : seen) {
                freq.merge(s, 1, Integer::sum);
            }
        }
        String ans = "";
        int maxFreq = 0;
        for (Map.Entry<String, Integer> e : freq.entrySet()) {
            if (e.getValue() > maxFreq || (e.getValue() == maxFreq && e.getKey().compareTo(ans) < 0)) {
                maxFreq = e.getValue();
                ans = e.getKey();
            }
        }
        return ans;
    }

    public boolean isCorrect(List<List<String>> responses, String output) {
        return findCommonResponse(responses).equals(output);
    }
}