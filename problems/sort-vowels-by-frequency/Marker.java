import java.util.*;
class Marker {
    public String sortVowels(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        Map<Character, Integer> first = new HashMap<>();
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (vowels.contains(c)) {
                freq.merge(c, 1, Integer::sum);
                first.putIfAbsent(c, i);
            }
        }
        List<Character> vs = new ArrayList<>(freq.keySet());
        vs.sort((a, b) -> {
            if (!freq.get(a).equals(freq.get(b))) return freq.get(b) - freq.get(a);
            return first.get(a) - first.get(b);
        });
        StringBuilder vals = new StringBuilder();
        Map<Character, Integer> rem = new HashMap<>(freq);
        int idx = 0;
        List<Character> expanded = new ArrayList<>();
        for (char v : vs) for (int i = 0; i < rem.get(v); i++) expanded.add(v);
        char[] arr = s.toCharArray();
        int p = 0;
        for (int i = 0; i < arr.length; i++) {
            if (vowels.contains(arr[i])) arr[i] = expanded.get(p++);
        }
        return new String(arr);
    }

    public boolean isCorrect(String s, String output) {
        return sortVowels(s).equals(output);
    }
}
