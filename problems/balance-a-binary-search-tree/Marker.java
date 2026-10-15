import java.util.*;
class Marker {
  public TreeNode balanceBST(TreeNode root) {
    List<Integer> nums = new ArrayList<>();
    inorder(root, nums);
    return build(nums, 0, nums.size() - 1);
  }

  private void inorder(TreeNode root, List<Integer> nums) {
    if (root == null)
      return;
    inorder(root.left, nums);
    nums.add(root.val);
    inorder(root.right, nums);
  }

  // Same as 108. Convert Sorted Array to Binary Search Tree
  private TreeNode build(List<Integer> nums, int l, int r) {
    if (l > r)
      return null;
    final int m = (l + r) / 2;
    return new TreeNode(nums.get(m), build(nums, l, m - 1), build(nums, m + 1, r));
  }

    public boolean isCorrect(TreeNode root, TreeNode output) {
        TreeNode expected = balanceBST(root);
        return sameTree(expected, output);
    }

    private static boolean sameTree(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        if (a.val != b.val) return false;
        return sameTree(a.left, b.left) && sameTree(a.right, b.right);
    }
}
