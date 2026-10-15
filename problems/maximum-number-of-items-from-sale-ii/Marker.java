import java.util.*;

class Marker {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        Map<Integer, Integer> minPrice = new HashMap<>();
        for (int[] it : items) {
            int f = it[0], p = it[1];
            minPrice.put(f, Math.min(minPrice.getOrDefault(f, Integer.MAX_VALUE), p));
        }
        
        List<Integer> factors = new ArrayList<>(minPrice.keySet());
        Collections.sort(factors);
        int m = factors.size();
        
        int[] gain = new int[m];
        for (int i = 0; i < m; i++) {
            int fi = factors.get(i);
            for (int j = 0; j < m; j++) {
                if (i != j && factors.get(j) % fi == 0) gain[i]++;
            }
        }
        
        List<int[]> units = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int price = minPrice.get(factors.get(i));
            int g = gain[i];
            for (int k = 0; k < g; k++) {
                units.add(new int[]{price, 2});
            }
        }
        
        int minPriceAll = Integer.MAX_VALUE;
        for (int p : minPrice.values()) minPriceAll = Math.min(minPriceAll, p);
        
        Collections.sort(units, Comparator.comparingInt(a -> a[0]));
        
        int ans = 0;
        int cost = 0;
        for (int[] u : units) {
            if (cost + u[0] > budget) break;
            cost += u[0];
            ans += u[1];
        }
        
        ans += (budget - cost) / minPriceAll;
        return ans;
    }

    public boolean isCorrect(int[][] items, int budget, int output) {
        return output == maximumSaleItems(items, budget);
    }
}