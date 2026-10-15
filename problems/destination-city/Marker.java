import java.util.*;
class Marker {
    public String destCity(List<List<String>> paths) {
        Set<String> starts = new HashSet<>();
        for (List<String> p : paths) starts.add(p.get(0));
        for (List<String> p : paths) {
            if (!starts.contains(p.get(1))) return p.get(1);
        }
        return "";
    }

    public boolean isCorrect(List<List<String>> paths, String output) {
        return destCity(paths).equals(output);
    }
}
