import java.util.*;
class Marker {
  public TreeNode sufficientSubset(TreeNode root, int limit) {
    if (root == null)
      return null;
    if (root.left == null && root.right == null)
      return root.val < limit ? null : root;
    root.left = sufficientSubset(root.left, limit - root.val);
    root.right = sufficientSubset(root.right, limit - root.val);
    return root.left == null && root.right == null ? null : root;
  }

    public boolean isCorrect(TreeNode root, int limit, TreeNode output) {
        TreeNode expected = sufficientSubset(root, limit);
        return sameTree(expected, output);
    }

    private static boolean sameTree(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        if (a.val != b.val) return false;
        return sameTree(a.left, b.left) && sameTree(a.right, b.right);
    }
}
