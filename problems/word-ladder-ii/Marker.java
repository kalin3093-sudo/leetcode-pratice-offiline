import java.util.*;

class Marker {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        List<List<String>> res = new ArrayList<>();
        if (!dict.contains(endWord)) return res;
        Set<String> visited = new HashSet<>();
        Queue<String> q = new ArrayDeque<>();
        q.offer(beginWord);
        visited.add(beginWord);
        Map<String, List<String>> parents = new HashMap<>();
        boolean found = false;
        while (!q.isEmpty() && !found) {
            int size = q.size();
            Set<String> levelVisited = new HashSet<>();
            for (int i = 0; i < size; i++) {
                String w = q.poll();
                char[] arr = w.toCharArray();
                for (int j = 0; j < arr.length; j++) {
                    char orig = arr[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == orig) continue;
                        arr[j] = c;
                        String nw = new String(arr);
                        if (nw.equals(endWord) && dict.contains(nw)) found = true;
                        if (dict.contains(nw) && !visited.contains(nw)) {
                            parents.computeIfAbsent(nw, k -> new ArrayList<>()).add(w);
                            if (levelVisited.add(nw)) q.offer(nw);
                        }
                    }
                    arr[j] = orig;
                }
            }
            visited.addAll(levelVisited);
        }
        if (found) {
            Deque<String> path = new ArrayDeque<>();
            path.addLast(endWord);
            dfs(endWord, beginWord, parents, path, res);
        }
        return res;
    }

    private void dfs(String word, String begin, Map<String, List<String>> parents,
                     Deque<String> path, List<List<String>> res) {
        if (word.equals(begin)) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (String p : parents.getOrDefault(word, Collections.emptyList())) {
            path.addFirst(p);
            dfs(p, begin, parents, path, res);
            path.removeFirst();
        }
    }

    public boolean isCorrect(String beginWord, String endWord, List<String> wordList, List<List<String>> output) {
        List<List<String>> ref = findLadders(beginWord, endWord, wordList);
        if (ref.size() != output.size()) return false;
        Set<String> a = new HashSet<>();
        for (List<String> l : ref) a.add(String.join("\u0001", l));
        Set<String> b = new HashSet<>();
        for (List<String> l : output) b.add(String.join("\u0001", l));
        return a.equals(b);
    }
}
