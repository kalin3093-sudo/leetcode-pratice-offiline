import java.util.*;
class Marker {
    public int maxFreqSum(String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) cnt[c - 'a']++;
        int maxVowel = 0, maxConsonant = 0;
        for (int i = 0; i < 26; i++) {
            char c = (char)('a' + i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                maxVowel = Math.max(maxVowel, cnt[i]);
            } else {
                maxConsonant = Math.max(maxConsonant, cnt[i]);
            }
        }
        return maxVowel + maxConsonant;
    }

    public boolean isCorrect(String s, int output) {
        return maxFreqSum(s) == output;
    }
}