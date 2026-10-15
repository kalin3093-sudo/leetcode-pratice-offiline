import java.util.*;
enum State { INIT, VISITING, VISITED }

class Marker {
  public List<Integer> eventualSafeNodes(int[][] graph) {
    List<Integer> ans = new ArrayList<>();
    State[] states = new State[graph.length];

    for (int i = 0; i < graph.length; ++i)
      if (!hasCycle(graph, i, states))
        ans.add(i);

    return ans;
  }

  private boolean hasCycle(int[][] graph, int u, State[] states) {
    if (states[u] == State.VISITING)
      return true;
    if (states[u] == State.VISITED)
      return false;
    states[u] = State.VISITING;
    for (final int v : graph[u])
      if (hasCycle(graph, v, states))
        return true;
    states[u] = State.VISITED;
    return false;
  }

    public boolean isCorrect(int[][] graph, List<Integer> output) {
        List<Integer> expected = eventualSafeNodes(graph);
        return expected.equals(output);
    }
}
