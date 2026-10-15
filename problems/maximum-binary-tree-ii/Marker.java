import java.util.*;
class Marker {
  public TreeNode insertIntoMaxTree(TreeNode root, int val) {
    if (root == null)
      return new TreeNode(val);
    if (root.val < val)
      return new TreeNode(val, root, null);
    root.right = insertIntoMaxTree(root.right, val);
    return root;
  }

    public boolean isCorrect(TreeNode root, int val, TreeNode output) {
        TreeNode expected = insertIntoMaxTree(root, val);
        return sameTree(expected, output);
    }

    private static boolean sameTree(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        if (a.val != b.val) return false;
        return sameTree(a.left, b.left) && sameTree(a.right, b.right);
    }
}
