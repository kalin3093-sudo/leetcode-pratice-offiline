import java.util.*;
class Marker {
    public int minCost(String source, String target, List<List<String>> rules, int[] costs) {
        int n = source.length();
        int m = rules.size();
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        // Preprocess rules: extract pattern, replacement, cost, starCount
        Rule[] ruleArr = new Rule[m];
        for (int i = 0; i < m; i++) {
            String pattern = rules.get(i).get(0);
            String repl = rules.get(i).get(1);
            int starCount = 0;
            for (char c : pattern.toCharArray()) if (c == '*') starCount++;
            ruleArr[i] = new Rule(pattern, repl, costs[i] + starCount, pattern.length());
        }

        for (int i = 0; i < n; i++) {
            if (dp[i] == Integer.MAX_VALUE) continue;
            // Option 1: keep character if matches
            if (source.charAt(i) == target.charAt(i)) {
                dp[i + 1] = Math.min(dp[i + 1], dp[i]);
            }
            // Option 2: apply rules
            for (Rule r : ruleArr) {
                int len = r.len;
                if (i + len > n) continue;
                if (matches(source, target, i, r)) {
                    dp[i + len] = Math.min(dp[i + len], dp[i] + r.cost);
                }
            }
        }
        return dp[n] == Integer.MAX_VALUE ? -1 : dp[n];
    }

    private boolean matches(String source, String target, int pos, Rule r) {
        String pattern = r.pattern;
        String repl = r.repl;
        for (int j = 0; j < r.len; j++) {
            char pc = pattern.charAt(j);
            if (pc != '*' && pc != source.charAt(pos + j)) return false;
            if (repl.charAt(j) != target.charAt(pos + j)) return false;
        }
        return true;
    }

    static class Rule {
        String pattern;
        String repl;
        int cost;
        int len;
        Rule(String pattern, String repl, int cost, int len) {
            this.pattern = pattern;
            this.repl = repl;
            this.cost = cost;
            this.len = len;
        }
    }

    public boolean isCorrect(String source, String target, List<List<String>> rules, int[] costs, int output) {
        int expected = minCost(source, target, rules, costs);
        return expected == output;
    }
}