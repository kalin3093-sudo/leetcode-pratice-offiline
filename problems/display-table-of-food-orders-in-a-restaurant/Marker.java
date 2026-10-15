import java.util.*;
class Marker {
    public List<List<String>> displayTable(List<List<String>> orders) {
        Set<String> foods = new TreeSet<>();
        Map<Integer, Map<String, Integer>> tableFoodCount = new TreeMap<>();
        
        for (List<String> order : orders) {
            int table = Integer.parseInt(order.get(1));
            String food = order.get(2);
            foods.add(food);
            tableFoodCount.computeIfAbsent(table, k -> new HashMap<>())
                .merge(food, 1, Integer::sum);
        }
        
        List<List<String>> result = new ArrayList<>();
        List<String> header = new ArrayList<>();
        header.add("Table");
        header.addAll(foods);
        result.add(header);
        
        for (Map.Entry<Integer, Map<String, Integer>> entry : tableFoodCount.entrySet()) {
            List<String> row = new ArrayList<>();
            row.add(String.valueOf(entry.getKey()));
            for (String food : foods) {
                row.add(String.valueOf(entry.getValue().getOrDefault(food, 0)));
            }
            result.add(row);
        }
        return result;
    }

    public boolean isCorrect(List<List<String>> orders, List<List<String>> output) {
        List<List<String>> expected = displayTable(orders);
        if (output == null || output.size() != expected.size()) return false;
        for (int i = 0; i < expected.size(); i++) {
            if (!expected.get(i).equals(output.get(i))) return false;
        }
        return true;
    }
}