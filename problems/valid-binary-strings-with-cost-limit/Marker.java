import java.util.*;

class Marker {
    public List<String> generateValidStrings(int n, int k) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        build(n, k, 0, 0, sb, res);
        return res;
    }

    private void build(int n, int k, int i, int cost, StringBuilder sb, List<String> res) {
        if (cost > k) return;
        if (i == n) {
            res.add(sb.toString());
            return;
        }
        sb.append('0');
        build(n, k, i + 1, cost, sb, res);
        sb.setLength(sb.length() - 1);
        if (i == 0 || sb.charAt(i - 1) != '1') {
            sb.append('1');
            build(n, k, i + 1, cost + i, sb, res);
            sb.setLength(sb.length() - 1);
        }
    }

    public boolean isCorrect(int n, int k, List<String> output) {
        if (output == null) return false;
        List<String> a = new ArrayList<>(output);
        List<String> b = generateValidStrings(n, k);
        Collections.sort(a);
        Collections.sort(b);
        return a.equals(b);
    }
}
