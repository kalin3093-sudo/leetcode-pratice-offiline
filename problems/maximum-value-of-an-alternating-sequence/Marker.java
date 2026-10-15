import java.util.*;
class Marker {
    public long maximumValue(int n, int s, int m) {
        if (n == 1) return s;
        
        // Pattern 1: s < x1 > x2 < x3 > ... (start with increase)
        // Peak values: s + m, s + 2m - 1, s + 3m - 2, ...
        // For odd positions (1-indexed): value = s + m + (pos-1)*(m-1)
        // Maximum peak at position ceil((n-1)/2) * 2 - 1? 
        // Let's think: positions 1, 3, 5... are peaks
        // Number of peaks = (n + 1) / 2 for pattern starting with increase? No.
        // Pattern: s (valley) < x1 (peak) > x2 (valley) < x3 (peak) ...
        // Peaks at odd indices (1, 3, 5...), valleys at even (0, 2, 4...)
        // First peak: s + m
        // Second peak: x1 - 1 + m = s + m - 1 + m = s + 2m - 1
        // Third peak: s + 3m - 2
        // k-th peak: s + k*m - (k-1) = s + k*(m-1) + 1? Let's check:
        // k=1: s + m = s + 1*(m-1) + 1 = s + m. Correct.
        // k=2: s + 2m - 1 = s + 2*(m-1) + 1 = s + 2m - 1. Correct.
        // k-th peak: s + k*(m-1) + 1
        
        // Number of peaks in pattern 1 (start with increase): ceil((n-1)/2) + 1? 
        // n=2: indices 0,1. Peak at 1. Count=1 = (2)/2 = 1
        // n=3: indices 0,1,2. Peaks at 1. Count=1? Wait: 0(valley) < 1(peak) > 2(valley). Only 1 peak.
        // n=4: 0<1>2<3. Peaks at 1,3. Count=2 = 4/2
        // Number of peaks = n/2 for even n, (n-1)/2 for odd n? n=3: (3-1)/2=1. n=4: 4/2=2.
        // So peaks = n/2 (integer division)
        
        long peaks1 = n / 2;
        long max1 = 0;
        if (peaks1 > 0) {
            max1 = s + peaks1 * m - (peaks1 - 1);
        } else {
            max1 = s;
        }
        
        // Pattern 2: s > x1 < x2 > x3 < ... (start with decrease)
        // First element is a peak (s), then valley, then peak, etc.
        // Peaks at even indices (0, 2, 4...)
        // First peak: s
        // Second peak: x1 + m = (s - 1) + m = s + m - 1
        // Third peak: s + 2m - 2
        // k-th peak (0-indexed): s + k*(m-1)
        // Number of peaks = (n + 1) / 2
        long peaks2 = (n + 1L) / 2;
        long max2 = s + (peaks2 - 1) * (m - 1);
        
        return Math.max(max1, max2);
    }

    public boolean isCorrect(int n, int s, int m, long output) {
        long expected = maximumValue(n, s, m);
        return expected == output;
    }
}