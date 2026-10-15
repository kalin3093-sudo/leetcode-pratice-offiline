import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public int widthOfBinaryTree(TreeNode root) {
    if (root == null)
      return 0;

    int ans = 0;
    // {node, index}
    Deque<Pair<TreeNode, Integer>> q = new ArrayDeque<>(List.of(new Pair<>(root, 1)));

    while (!q.isEmpty()) {
      final int offset = q.peekFirst().getValue() * 2;
      ans = Math.max(ans, q.peekLast().getValue() - q.peekFirst().getValue() + 1);
      for (int sz = q.size(); sz > 0; --sz) {
        final TreeNode node = q.peekFirst().getKey();
        final int index = q.pollFirst().getValue();
        if (node.left != null)
          q.offer(new Pair<>(node.left, index * 2 - offset));
        if (node.right != null)
          q.offer(new Pair<>(node.right, index * 2 + 1 - offset));
      }
    }

    return ans;
  }

    public boolean isCorrect(TreeNode root, int output) {
        int expected = widthOfBinaryTree(root);
        return expected == output;
    }
}
class Pair<K, V> {
    private final K key;
    private final V value;
    Pair(K key, V value) { this.key = key; this.value = value; }
    public K getKey() { return key; }
    public V getValue() { return value; }
}
