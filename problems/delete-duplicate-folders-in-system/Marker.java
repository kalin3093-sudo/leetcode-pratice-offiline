import java.util.*;
class TrieNode {
  public Map<String, TrieNode> children = new HashMap<>();
  public boolean deleted = false;
}

class Marker {
  public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
    List<List<String>> ans = new ArrayList<>();
    Map<String, List<TrieNode>> subtreeToNodes = new HashMap<>();

    Collections.sort(paths,
                     Comparator.<List<String>>comparingInt(List::size).thenComparing((a, b) -> {
                       for (int i = 0; i < Math.min(a.size(), b.size()); ++i) {
                         final int c = a.get(i).compareTo(b.get(i));
                         if (c != 0)
                           return c;
                       }
                       return 0;
                     }));

    for (List<String> path : paths) {
      TrieNode node = root;
      for (final String s : path) {
        node.children.putIfAbsent(s, new TrieNode());
        node = node.children.get(s);
      }
    }

    buildSubtreeToRoots(root, subtreeToNodes);

    for (List<TrieNode> nodes : subtreeToNodes.values())
      if (nodes.size() > 1)
        for (TrieNode node : nodes)
          node.deleted = true;

    constructPath(root, new ArrayList<>(), ans);
    return ans;
  }

  private TrieNode root = new TrieNode();

  private StringBuilder buildSubtreeToRoots(TrieNode node,
                                            Map<String, List<TrieNode>> subtreeToNodes) {
    StringBuilder sb = new StringBuilder("(");
    for (final String s : node.children.keySet()) {
      TrieNode child = node.children.get(s);
      sb.append(s).append(buildSubtreeToRoots(child, subtreeToNodes));
    }
    sb.append(")");
    final String subtree = sb.toString();
    if (!subtree.equals("()")) {
      subtreeToNodes.putIfAbsent(subtree, new ArrayList<>());
      subtreeToNodes.get(subtree).add(node);
    }
    return sb;
  }

  private void constructPath(TrieNode node, List<String> path, List<List<String>> ans) {
    for (final String s : node.children.keySet()) {
      TrieNode child = node.children.get(s);
      if (!child.deleted) {
        path.add(s);
        constructPath(child, path, ans);
        path.remove(path.size() - 1);
      }
    }
    if (!path.isEmpty())
      ans.add(new ArrayList<>(path));
  }

      static int[] sortedI(int[] a){int[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static int[][] sortedI2(int[][] a){int[][] b=a.clone();java.util.Arrays.sort(b,(x,y)->java.util.Arrays.compare(x,y));return b;}
  static String[] sortedA(String[] a){String[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static <T extends Comparable<? super T>> java.util.List<T> sortedL(java.util.List<T> l){java.util.List<T> c=new java.util.ArrayList<>(l);java.util.Collections.sort(c);return c;}
  static java.util.List<String> sortedLL(java.util.List<?> l){java.util.List<String> c=new java.util.ArrayList<>();for(Object o:l)c.add(String.valueOf(o));java.util.Collections.sort(c);return c;}
  static String sortedS(String s){char[] c=s.toCharArray();java.util.Arrays.sort(c);return new String(c);}
  
  public boolean isCorrect(List<List<String>> paths, List<List<String>> output) {
        List<List<String>> expected = deleteDuplicateFolder(paths);
        return sortedLL(expected).equals(sortedLL(output));
    }
}
