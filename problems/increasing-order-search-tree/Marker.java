import java.util.*;
class Marker {
  public TreeNode increasingBST(TreeNode root) {
    return increasingBST(root, null);
  }

  private TreeNode increasingBST(TreeNode root, TreeNode tail) {
    if (root == null)
      return tail;

    TreeNode ans = increasingBST(root.left, root);
    root.left = null;
    root.right = increasingBST(root.right, tail);
    return ans;
  }

    public boolean isCorrect(TreeNode root, TreeNode output) {
        TreeNode expected = increasingBST(root);
        return sameTree(expected, output);
    }

    private static boolean sameTree(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        if (a.val != b.val) return false;
        return sameTree(a.left, b.left) && sameTree(a.right, b.right);
    }
}
