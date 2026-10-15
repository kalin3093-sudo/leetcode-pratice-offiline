import java.util.*;
class Marker {
  public List<String> addOperators(String num, int target) {
    List<String> ans = new ArrayList<>();
    dfs(num, target, 0, 0, 0, new StringBuilder(), ans);
    return ans;
  }

  private void dfs(String num, int target, int s, long prev, long eval, StringBuilder sb,
                   List<String> ans) {
    if (s == num.length()) {
      if (eval == target)
        ans.add(sb.toString());
      return;
    }

    for (int i = s; i < num.length(); ++i) {
      if (i > s && num.charAt(s) == '0')
        return;
      final long curr = Long.parseLong(num.substring(s, i + 1));
      final int length = sb.length();
      if (s == 0) { // the first number
        dfs(num, target, i + 1, curr, curr, sb.append(curr), ans);
        sb.setLength(length);
      } else {
        dfs(num, target, i + 1, curr, eval + curr, sb.append("+").append(curr), ans);
        sb.setLength(length);
        dfs(num, target, i + 1, -curr, eval - curr, sb.append("-").append(curr), ans);
        sb.setLength(length);
        dfs(num, target, i + 1, prev * curr, eval - prev + prev * curr, sb.append("*").append(curr),
            ans);
        sb.setLength(length);
      }
    }
  }

    public boolean isCorrect(String num, int target, List<String> output) {
        if (output == null) return false;
        for (String expr : output)
            if (!evaluatesTo(num, target, expr)) return false;
        return true;
    }

    private boolean evaluatesTo(String num, int target, String expr) {
        if (expr == null || expr.isEmpty()) return false;
        List<Long> vals = new ArrayList<>();
        List<Character> ops = new ArrayList<>();
        StringBuilder allDigits = new StringBuilder();
        int i = 0;
        final int len = expr.length();
        while (i < len) {
            int start = i;
            while (i < len && expr.charAt(i) >= '0' && expr.charAt(i) <= '9') i++;
            if (i == start) return false; // operator without operand (e.g. leading '-'/'+')
            String d = expr.substring(start, i);
            if (d.length() > 1 && d.charAt(0) == '0') return false; // leading zero
            allDigits.append(d);
            vals.add(Long.parseLong(d));
            if (i == len) break;
            char op = expr.charAt(i);
            if (op != '+' && op != '-' && op != '*') return false;
            ops.add(op);
            i++;
        }
        // must use all digits of num, in order
        if (!allDigits.toString().equals(num)) return false;
        // evaluate with * precedence
        long eval = 0;
        long prev = 0;
        for (int k = 0; k < vals.size(); k++) {
            long v = vals.get(k);
            if (k == 0) {
                eval = v;
                prev = v;
                continue;
            }
            char op = ops.get(k - 1);
            if (op == '+') {
                eval += v;
                prev = v;
            } else if (op == '-') {
                eval -= v;
                prev = -v;
            } else {
                eval = eval - prev + prev * v;
                prev = prev * v;
            }
        }
        return eval == target;
    }
}
