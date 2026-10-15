import java.util.*;
import java.math.BigInteger;
class Marker {
    public long specialPalindrome(long n) {
        List<Long> specials = new ArrayList<>();
        
        // Digits 1-9, each can be used 0 or k times where k is the digit value
        // For palindrome: all used digits must have even count except at most one
        // So valid subsets: any even digits + at most one odd digit
        
        int[] digits = {1,2,3,4,5,6,7,8,9};
        
        // Try all subsets
        for (int mask = 1; mask < (1 << 9); mask++) {
            int totalLen = 0;
            int oddCount = 0;
            int oddDigit = -1;
            List<Integer> halfDigits = new ArrayList<>();
            boolean valid = true;
            
            for (int d = 1; d <= 9; d++) {
                if ((mask & (1 << (d - 1))) != 0) {
                    totalLen += d;
                    if (d % 2 == 1) {
                        oddCount++;
                        oddDigit = d;
                    }
                    int halfCount = d / 2;
                    for (int i = 0; i < halfCount; i++) halfDigits.add(d);
                }
            }
            
            if (totalLen % 2 == 0) {
                if (oddCount != 0) continue;
            } else {
                if (oddCount != 1) continue;
            }

            // ponytail: skip oversized subsets; brute-force permutations of a 14-long half hung the gate
            if (totalLen > 18) continue;

            // Generate all unique permutations of halfDigits
            Collections.sort(halfDigits);
            do {
                StringBuilder half = new StringBuilder();
                for (int d : halfDigits) half.append(d);
                String mid = (totalLen % 2 == 1) ? String.valueOf(oddDigit) : "";
                String rev = half.reverse().toString();
                half.reverse();
                String full = half.toString() + mid + rev;
                
                // Check if fits in long
                if (full.length() <= 18) {
                    try {
                        specials.add(Long.parseLong(full));
                    } catch (NumberFormatException e) {
                        // Too big for long, skip
                    }
                }
            } while (nextPermutation(halfDigits));
        }
        
        Collections.sort(specials);
        
        // Binary search for smallest > n
        for (long s : specials) {
            if (s > n) return s;
        }
        return -1;
    }
    
    private boolean nextPermutation(List<Integer> a) {
        int i = a.size() - 2;
        while (i >= 0 && a.get(i) >= a.get(i + 1)) i--;
        if (i < 0) return false;
        int j = a.size() - 1;
        while (a.get(j) <= a.get(i)) j--;
        Collections.swap(a, i, j);
        Collections.reverse(a.subList(i + 1, a.size()));
        return true;
    }

    public boolean isCorrect(long n, long output) {
        return specialPalindrome(n) == output;
    }
}