import java.util.*;

class Marker {
    public List<List<String>> wordSquares(String[] words) {
        int n = words.length;
        List<List<String>> res = new ArrayList<>();
        for (int a = 0; a < n; a++)
            for (int b = 0; b < n; b++) {
                if (b == a) continue;
                for (int c = 0; c < n; c++) {
                    if (c == a || c == b) continue;
                    for (int d = 0; d < n; d++) {
                        if (d == a || d == b || d == c) continue;
                        String top = words[a], left = words[b], right = words[c], bottom = words[d];
                        if (top.charAt(0) == left.charAt(0) && top.charAt(3) == right.charAt(0)
                                && bottom.charAt(0) == left.charAt(3) && bottom.charAt(3) == right.charAt(3)) {
                            res.add(Arrays.asList(top, left, right, bottom));
                        }
                    }
                }
            }
        res.sort((x, y) -> {
            for (int i = 0; i < 4; i++) {
                int cmp = x.get(i).compareTo(y.get(i));
                if (cmp != 0) return cmp;
            }
            return 0;
        });
        return res;
    }

    public boolean isCorrect(String[] words, List<List<String>> output) {
        List<List<String>> ref = wordSquares(words);
        if (ref.size() != output.size()) return false;
        Set<String> a = new HashSet<>();
        for (List<String> l : ref) a.add(String.join("\u0001", l));
        Set<String> b = new HashSet<>();
        for (List<String> l : output) b.add(String.join("\u0001", l));
        return a.equals(b);
    }
}
