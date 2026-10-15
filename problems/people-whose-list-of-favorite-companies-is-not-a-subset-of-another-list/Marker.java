import java.util.*;

class Marker {
  public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
    int n = favoriteCompanies.size();
    List<Set<String>> sets = new ArrayList<>();
    for (List<String> l : favoriteCompanies)
      sets.add(new HashSet<>(l));
    List<Integer> res = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      boolean ok = true;
      for (int j = 0; j < n; j++) {
        if (i != j && sets.get(j).containsAll(sets.get(i))) {
          ok = false;
          break;
        }
      }
      if (ok)
        res.add(i);
    }
    return res;
  }

  public boolean isCorrect(List<List<String>> favoriteCompanies, List<Integer> output) {
    return output != null && output.equals(peopleIndexes(favoriteCompanies));
  }
}
