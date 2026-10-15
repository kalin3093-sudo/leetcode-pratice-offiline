import java.util.*;
class Marker {
  public int findTilt(TreeNode root) {
    sum(root);
    return ans;
  }

  private int ans = 0;

  private int sum(TreeNode root) {
    if (root == null)
      return 0;

    final int l = sum(root.left);
    final int r = sum(root.right);
    ans += Math.abs(l - r);
    return root.val + l + r;
  }

    public boolean isCorrect(TreeNode root, int output) {
        int expected = findTilt(root);
        return expected == output;
    }
}
