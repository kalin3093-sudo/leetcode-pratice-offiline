import java.util.*;
class Marker {
    public int[] decimalRepresentation(int n) {
        List<Integer> res = new ArrayList<>();
        long place = 1;
        while (n > 0) {
            int d = n % 10;
            if (d != 0) res.add((int) (d * place));
            n /= 10;
            place *= 10;
        }
        Collections.reverse(res);
        int[] arr = new int[res.size()];
        for (int i = 0; i < arr.length; i++) arr[i] = res.get(i);
        return arr;
    }

    public boolean isCorrect(int n, int[] output) {
        if (output == null) return false;
        return Arrays.equals(decimalRepresentation(n), output);
    }
}
