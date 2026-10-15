import java.util.*;
class Marker {
  public String tree2str(TreeNode t) {
    return dfs(t);
  }

  private String dfs(TreeNode root) {
    if (root == null)
      return "";
    if (root.right != null)
      return root.val + "(" + dfs(root.left) + ")(" + dfs(root.right) + ")";
    if (root.left != null)
      return root.val + "(" + dfs(root.left) + ")";
    return root.val + "";
  }

    public boolean isCorrect(TreeNode root, String output) {
        String expected = tree2str(root);
        return expected.equals(output);
    }
}
