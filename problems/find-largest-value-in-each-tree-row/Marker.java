import java.util.*;
class Marker {
  public List<Integer> largestValues(TreeNode root) {
    if (root == null)
      return new ArrayList<>();

    List<Integer> ans = new ArrayList<>();
    Queue<TreeNode> q = new ArrayDeque<>(List.of(root));

    while (!q.isEmpty()) {
      int mx = Integer.MIN_VALUE;
      for (int sz = q.size(); sz > 0; --sz) {
        TreeNode node = q.poll();
        mx = Math.max(mx, node.val);
        if (node.left != null)
          q.offer(node.left);
        if (node.right != null)
          q.offer(node.right);
      }
      ans.add(mx);
    }

    return ans;
  }

    public boolean isCorrect(TreeNode root, List<Integer> output) {
        List<Integer> expected = largestValues(root);
        return expected.equals(output);
    }
}
