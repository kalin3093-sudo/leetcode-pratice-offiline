import java.util.*;
class Marker {
    public String removeSubstring(String s, int k) {
        List<int[]> stack = new ArrayList<>(); // [0]='('?1:0, [1]=count
        for (char c : s.toCharArray()) {
            int t = c == '(' ? 1 : 0;
            if (!stack.isEmpty() && stack.get(stack.size() - 1)[0] == t) stack.get(stack.size() - 1)[1]++;
            else stack.add(new int[]{ t, 1 });
            while (stack.size() >= 2) {
                int[] open = stack.get(stack.size() - 2);
                int[] close = stack.get(stack.size() - 1);
                if (open[0] == 1 && close[0] == 0 && open[1] >= k && close[1] >= k) {
                    open[1] -= k; close[1] -= k;
                    if (close[1] == 0) stack.remove(stack.size() - 1);
                    if (open[1] == 0) stack.remove(stack.size() - 2 > stack.size() - 1 ? stack.size() - 1 : stack.size() - 2);
                    // simpler: rebuild
                    if (open[1] == 0 && stack.size() >= 2 && stack.get(stack.size() - 2) == open) stack.remove(stack.size() - 2);
                    continue;
                }
                break;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int[] run : stack)
            for (int i = 0; i < run[1]; i++) sb.append(run[0] == 1 ? '(' : ')');
        return sb.toString();
    }

    public boolean isCorrect(String s, int k, String output) {
        return removeSubstring(s, k).equals(output);
    }
}
