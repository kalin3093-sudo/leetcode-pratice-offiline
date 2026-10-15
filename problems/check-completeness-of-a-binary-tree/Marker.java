import java.util.*;
class Marker {
  public boolean isCompleteTree(TreeNode root) {
    if (root == null)
      return true;

    Queue<TreeNode> q = new LinkedList<>(Arrays.asList(root));

    while (q.peek() != null) {
      TreeNode node = q.poll();
      q.offer(node.left);
      q.offer(node.right);
    }

    while (!q.isEmpty() && q.peek() == null)
      q.poll();

    return q.isEmpty();
  }

    public boolean isCorrect(TreeNode root, boolean output) {
        boolean expected = isCompleteTree(root);
        return expected == output;
    }
}
