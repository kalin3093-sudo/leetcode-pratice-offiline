import java.util.*;
class Marker {
  public List<List<Integer>> levelOrderBottom(TreeNode root) {
    if (root == null)
      return new ArrayList<>();

    List<List<Integer>> ans = new ArrayList<>();
    Queue<TreeNode> q = new ArrayDeque<>(List.of(root));

    while (!q.isEmpty()) {
      List<Integer> currLevel = new ArrayList<>();
      for (int sz = q.size(); sz > 0; --sz) {
        TreeNode node = q.poll();
        currLevel.add(node.val);
        if (node.left != null)
          q.offer(node.left);
        if (node.right != null)
          q.offer(node.right);
      }
      ans.add(currLevel);
    }

    Collections.reverse(ans);
    return ans;
  }

    public boolean isCorrect(TreeNode root, List<List<Integer>> output) {
        List<List<Integer>> expected = levelOrderBottom(root);
        return expected.equals(output);
    }
}
