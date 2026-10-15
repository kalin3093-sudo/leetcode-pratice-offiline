import java.util.*;
class Marker {
  public TreeNode subtreeWithAllDeepest(TreeNode root) {
    return dfs(root).lca;
  }

  private record T(TreeNode lca, int depth) {}

  private T dfs(TreeNode root) {
    if (root == null)
      return new T(null, 0);

    T left = dfs(root.left);
    T right = dfs(root.right);
    if (left.depth > right.depth)
      return new T(left.lca, left.depth + 1);
    if (left.depth < right.depth)
      return new T(right.lca, right.depth + 1);
    return new T(root, left.depth + 1);
  }

    public boolean isCorrect(TreeNode root, TreeNode output) {
        TreeNode expected = subtreeWithAllDeepest(root);
        return sameTree(expected, output);
    }

    private static boolean sameTree(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        if (a.val != b.val) return false;
        return sameTree(a.left, b.left) && sameTree(a.right, b.right);
    }
}
