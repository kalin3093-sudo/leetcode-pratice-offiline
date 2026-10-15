import java.util.*;
class Marker {
    int[] parent;
    int find(int x) { return parent[x] == x ? x : (parent[x] = find(parent[x])); }
    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        for (int i = 0; i < n; i++) {
            if (lcp[i][i] != n - i) return "";
            for (int j = i + 1; j < n; j++) {
                if (lcp[i][j] != lcp[j][i]) return "";
                if (lcp[i][j] > 0) {
                    for (int k = 0; k < lcp[i][j]; k++) {
                        if (i + k < n && j + k < n) parent[find(i + k)] = find(j + k);
                    }
                }
            }
        }
        char[] word = new char[n];
        Arrays.fill(word, '?');
        for (int i = 0; i < n; i++) {
            int root = find(i);
            if (word[root] == '?') {
                boolean[] used = new boolean[26];
                for (int j = 0; j < i; j++) {
                    if (find(j) == root) continue;
                    if (word[find(j)] != '?') used[word[find(j)] - 'a'] = true;
                }
                for (int c = 0; c < 26; c++) {
                    if (!used[c]) {
                        word[root] = (char)('a' + c);
                        break;
                    }
                }
                if (word[root] == '?') return "";
            }
            word[i] = word[root];
        }
        // verify
        int[][] myLcp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (word[i] == word[j]) {
                    myLcp[i][j] = 1;
                    if (i + 1 < n && j + 1 < n) myLcp[i][j] += myLcp[i + 1][j + 1];
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (myLcp[i][j] != lcp[i][j]) return "";
            }
        }
        return new String(word);
    }

    public boolean isCorrect(int[][] lcp, String output) {
        return findTheString(lcp).equals(output);
    }
}