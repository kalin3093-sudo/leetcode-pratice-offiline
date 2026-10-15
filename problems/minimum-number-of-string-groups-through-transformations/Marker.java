import java.util.*;

class Marker {
    public int minimumGroups(String[] words) {
        Set<String> signatures = new HashSet<>();
        for (String w : words) {
            StringBuilder even = new StringBuilder();
            StringBuilder odd = new StringBuilder();
            for (int i = 0; i < w.length(); i++) {
                if ((i & 1) == 0) even.append(w.charAt(i));
                else odd.append(w.charAt(i));
            }
            String canonEven = minCyclicRotation(even.toString());
            String canonOdd = minCyclicRotation(odd.toString());
            signatures.add(canonEven + "#" + canonOdd);
        }
        return signatures.size();
    }

    private String minCyclicRotation(String s) {
        if (s.isEmpty()) return s;
        String ss = s + s;
        int n = s.length();
        int i = 0, j = 1, k = 0;
        while (i < n && j < n && k < n) {
            char a = ss.charAt(i + k);
            char b = ss.charAt(j + k);
            if (a == b) {
                k++;
            } else {
                if (a > b) i = i + k + 1;
                else j = j + k + 1;
                if (i == j) i++;
                k = 0;
            }
        }
        int start = Math.min(i, j);
        return ss.substring(start, start + n);
    }

    public boolean isCorrect(String[] words, int output) {
        return minimumGroups(words) == output;
    }
}