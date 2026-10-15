import java.util.*;
class Marker {
    public int findMinimumTime(List<Integer> strength, int k) {
        int n = strength.size();
        int[] s = strength.stream().mapToInt(Integer::intValue).toArray();
        int best = Integer.MAX_VALUE;
        
        // Try all permutations (n <= 8)
        Integer[] perm = new Integer[n];
        for (int i = 0; i < n; i++) perm[i] = i;
        
        do {
            int time = 0;
            int energy = 0;
            int x = 1;
            for (int idx : perm) {
                int need = s[idx];
                // Wait until energy >= need
                int wait = (need - energy + x - 1) / x;
                if (wait < 0) wait = 0;
                time += wait;
                energy += wait * x;
                
                // Break the lock
                energy -= need;
                x += k;
            }
            best = Math.min(best, time);
        } while (nextPermutation(perm));
        
        return best;
    }
    
    private boolean nextPermutation(Integer[] a) {
        int i = a.length - 2;
        while (i >= 0 && a[i] >= a[i + 1]) i--;
        if (i < 0) return false;
        int j = a.length - 1;
        while (a[j] <= a[i]) j--;
        int tmp = a[i]; a[i] = a[j]; a[j] = tmp;
        for (int l = i + 1, r = a.length - 1; l < r; l++, r--) {
            tmp = a[l]; a[l] = a[r]; a[r] = tmp;
        }
        return true;
    }

    public boolean isCorrect(List<Integer> strength, int k, int output) {
        return findMinimumTime(strength, k) == output;
    }
}