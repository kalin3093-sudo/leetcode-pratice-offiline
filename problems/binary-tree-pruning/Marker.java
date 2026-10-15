import java.util.*;
class Marker {
  public TreeNode pruneTree(TreeNode root) {
    if (root == null)
      return null;
    root.left = pruneTree(root.left);
    root.right = pruneTree(root.right);
    if (root.left == null && root.right == null && root.val == 0)
      return null;
    return root;
  }

    public boolean isCorrect(TreeNode root, TreeNode output) {
        TreeNode expected = pruneTree(root);
        return sameTree(expected, output);
    }

    private static boolean sameTree(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        if (a.val != b.val) return false;
        return sameTree(a.left, b.left) && sameTree(a.right, b.right);
    }
}
