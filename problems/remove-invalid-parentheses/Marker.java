import java.util.*;
class Marker {
    public List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        Queue<String> q = new ArrayDeque<>();
        q.add(s);
        Set<String> seen = new HashSet<>();
        seen.add(s);
        boolean found = false;
        while (!q.isEmpty()) {
            String cur = q.poll();
            if (isValid(cur)) { ans.add(cur); found = true; }
            if (found) continue;
            for (int i = 0; i < cur.length(); i++) {
                char c = cur.charAt(i);
                if (c != '(' && c != ')') continue;
                String next = cur.substring(0, i) + cur.substring(i + 1);
                if (seen.add(next)) q.add(next);
            }
        }
        Collections.sort(ans);
        return ans;
    }

    private boolean isValid(String s) {
        int bal = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') bal++;
            else if (c == ')') { bal--; if (bal < 0) return false; }
        }
        return bal == 0;
    }

    public boolean isCorrect(String s, List<String> output) {
        List<String> expected = removeInvalidParentheses(s);
        List<String> a = new ArrayList<>(expected), b = new ArrayList<>(output);
        Collections.sort(a); Collections.sort(b);
        return a.equals(b);
    }
}
