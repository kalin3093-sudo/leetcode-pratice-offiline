import java.util.*;
class Marker {
    public long minimumCost(String s, String t, int flipCost, int swapCost, int crossCost) {
        int a = 0, b = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0' && t.charAt(i) == '1') a++;
            else if (s.charAt(i) == '1' && t.charAt(i) == '0') b++;
        }
        int p = Math.min(a, b);
        long cost = (long)p * Math.min(swapCost, 2L * flipCost);
        a -= p;
        b -= p;
        int r = a + b; // |a - b|
        int pairs = r / 2;
        cost += (long)pairs * Math.min(2L * flipCost, (long)crossCost + swapCost);
        if (r % 2 == 1) cost += flipCost;
        return cost;
    }

    public boolean isCorrect(String s, String t, int flipCost, int swapCost, int crossCost, long output) {
        long expected = minimumCost(s, t, flipCost, swapCost, crossCost);
        return expected == output;
    }
}