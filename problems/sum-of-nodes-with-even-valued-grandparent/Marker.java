import java.util.*;
class Marker {
  public int sumEvenGrandparent(TreeNode root) {
    return dfs(root, 1, 1); // The parent and the grandparent are odd at first.
  }

  private int dfs(TreeNode root, int p, int gp) {
    if (root == null)
      return 0;
    return (gp % 2 == 0 ? root.val : 0) + //
        dfs(root.left, root.val, p) +     //
        dfs(root.right, root.val, p);
  }

    public boolean isCorrect(TreeNode root, int output) {
        int expected = sumEvenGrandparent(root);
        return expected == output;
    }
}
