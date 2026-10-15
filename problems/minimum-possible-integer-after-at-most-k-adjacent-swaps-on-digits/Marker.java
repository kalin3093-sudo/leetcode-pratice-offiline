import java.util.*;
class Marker {
    public String minInteger(String num, int k) {
        int n = num.length();
        List<Integer>[] pos = new List[10];
        for (int d = 0; d < 10; d++) pos[d] = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            pos[num.charAt(i) - '0'].add(i);
        }
        
        int[] bit = new int[n + 1]; // 1-indexed, initially all zeros
        
        StringBuilder sb = new StringBuilder();
        int[] idx = new int[10];
        boolean[] used = new boolean[n];
        
        for (int targetPos = 0; targetPos < n && k > 0; targetPos++) {
            for (int d = 0; d < 10; d++) {
                if (idx[d] >= pos[d].size()) continue;
                int originalPos = pos[d].get(idx[d]);
                int usedBefore = query(bit, originalPos); // number of removed elements before this position (0-indexed)
                int remainingIndex = originalPos - usedBefore; // index in remaining array (0-indexed)
                if (remainingIndex <= k) {
                    sb.append(d);
                    k -= remainingIndex;
                    update(bit, originalPos + 1, 1); // mark as removed (1-indexed for BIT)
                    used[originalPos] = true;
                    idx[d]++;
                    break;
                }
            }
        }
        
        // Append remaining digits in original order
        for (int i = 0; i < n; i++) {
            if (!used[i]) {
                sb.append(num.charAt(i));
            }
        }
        
        return sb.toString();
    }
    
    private void update(int[] bit, int i, int delta) {
        for (; i < bit.length; i += i & -i) bit[i] += delta;
    }
    
    private int query(int[] bit, int i) {
        int sum = 0;
        for (; i > 0; i -= i & -i) sum += bit[i];
        return sum;
    }

    public boolean isCorrect(String num, int k, String output) {
        return minInteger(num, k).equals(output);
    }
}