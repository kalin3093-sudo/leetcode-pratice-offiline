import java.util.*;
class Marker {
  public TreeNode reverseOddLevels(TreeNode root) {
    dfs(root.left, root.right, true);
    return root;
  }

  private void dfs(TreeNode left, TreeNode right, boolean isOddLevel) {
    if (left == null)
      return;
    if (isOddLevel) {
      final int val = left.val;
      left.val = right.val;
      right.val = val;
    }
    dfs(left.left, right.right, !isOddLevel);
    dfs(left.right, right.left, !isOddLevel);
  }

    public boolean isCorrect(TreeNode root, TreeNode output) {
        TreeNode expected = reverseOddLevels(root);
        return sameTree(expected, output);
    }

    private static boolean sameTree(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        if (a.val != b.val) return false;
        return sameTree(a.left, b.left) && sameTree(a.right, b.right);
    }
}
