import java.util.*;
class Marker {
  public TreeNode convertBST(TreeNode root) {
    reversedInorder(root);
    return root;
  }

  private int prefix = 0;

  private void reversedInorder(TreeNode root) {
    if (root == null)
      return;

    reversedInorder(root.right);
    prefix += root.val;
    root.val = prefix;
    reversedInorder(root.left);
  }

    public boolean isCorrect(TreeNode root, TreeNode output) {
        TreeNode expected = convertBST(root);
        return sameTree(expected, output);
    }

    private static boolean sameTree(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        if (a.val != b.val) return false;
        return sameTree(a.left, b.left) && sameTree(a.right, b.right);
    }
}
