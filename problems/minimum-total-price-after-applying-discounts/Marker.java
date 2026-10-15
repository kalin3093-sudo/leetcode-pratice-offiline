import java.util.*;
class Marker {
    public double minPrice(int[] prices, int[] discounts) {
        Arrays.sort(prices);
        Arrays.sort(discounts);
        
        int p = prices.length - 1;
        int d = discounts.length - 1;
        double total = 0;
        
        while (p >= 0 && d >= 0) {
            total += prices[p] * (100 - discounts[d]) / 100.0;
            p--;
            d--;
        }
        
        while (p >= 0) {
            total += prices[p];
            p--;
        }
        
        return total;
    }

    public boolean isCorrect(int[] prices, int[] discounts, double output) {
        return Math.abs(minPrice(prices, discounts) - output) < 1e-5;
    }
}