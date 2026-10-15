import java.util.*;
class Marker {
    static final int MOD = 1_000_000_007;
    
    public int maxValue(int[] nums1, int[] nums0) {
        int n = nums1.length;
        Segment[] segs = new Segment[n];
        for (int i = 0; i < n; i++) {
            segs[i] = new Segment(nums1[i], nums0[i]);
        }
        
        Arrays.sort(segs, (a, b) -> {
            // Compare: which order gives larger value?
            // a followed by b vs b followed by a
            // Value = (2^a.zeros - 1) * 2^b.zeros * 2^a.ones + ...
            // Actually, the value when concatenating a then b:
            // a: ones=a1, zeros=a0 -> value = (2^a1 - 1) * 2^a0
            // a then b: val_a * 2^(b1+b0) + val_b
            // We want a before b if val_a * 2^(b1+b0) + val_b > val_b * 2^(a1+a0) + val_a
            // val_a * (2^(b1+b0) - 1) > val_b * (2^(a1+a0) - 1)
            // (2^a1 - 1) * 2^a0 * (2^(b1+b0) - 1) > (2^b1 - 1) * 2^b0 * (2^(a1+a0) - 1)
            // Simplify: compare a1/(a0+?) 
            // Key insight: sort by ratio of ones to zeros, or by the "density" of leading ones
            // Actually, compare a.ones * b.zeros vs b.ones * a.zeros for the leading part
            // More precisely: the segments should be sorted by the value they contribute as a prefix
            // The optimal order is to sort by the comparator: a comes before b if 
            // a.ones * (b.ones + b.zeros) + a.zeros * b.ones > b.ones * (a.ones + a.zeros) + b.zeros * a.ones
            // Wait, let's use the standard approach: the segment with more leading 1s relative to total length should come first
            // Comparator: a comes before b if a.ones * (b.ones + b.zeros) > b.ones * (a.ones + a.zeros)
            long lhs = (long) a.ones * (b.ones + b.zeros);
            long rhs = (long) b.ones * (a.ones + a.zeros);
            if (lhs != rhs) return Long.compare(rhs, lhs); // descending
            return Integer.compare(b.zeros, a.zeros);
        });
        
        long result = 0;
        for (Segment seg : segs) {
            // Add seg.ones 1s
            for (int i = 0; i < seg.ones; i++) {
                result = (result * 2 + 1) % MOD;
            }
            // Add seg.zeros 0s
            for (int i = 0; i < seg.zeros; i++) {
                result = (result * 2) % MOD;
            }
        }
        return (int) result;
    }
    
    static class Segment {
        int ones, zeros;
        Segment(int ones, int zeros) {
            this.ones = ones;
            this.zeros = zeros;
        }
    }

    public boolean isCorrect(int[] nums1, int[] nums0, int output) {
        int expected = maxValue(nums1, nums0);
        return expected == output;
    }
}