import java.util.*;
class Marker {
    public List<Integer> findGoodIntegers(int n) {
        Map<Integer, Integer> count = new HashMap<>();
        int maxA = (int) Math.cbrt(n) + 1;
        for (int a = 1; a <= maxA; a++) {
            long a3 = 1L * a * a * a;
            if (a3 > n) break;
            int maxB = (int) Math.cbrt(n - a3) + 1;
            for (int b = a; b <= maxB; b++) {
                long sum = a3 + 1L * b * b * b;
                if (sum > n) break;
                count.merge((int) sum, 1, Integer::sum);
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (Map.Entry<Integer, Integer> e : count.entrySet()) {
            if (e.getValue() >= 2) ans.add(e.getKey());
        }
        Collections.sort(ans);
        return ans;
    }

    public boolean isCorrect(int n, List<Integer> output) {
        List<Integer> expected = findGoodIntegers(n);
        return expected.equals(output);
    }
}