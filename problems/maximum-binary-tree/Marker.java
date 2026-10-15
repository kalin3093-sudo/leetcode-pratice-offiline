import java.util.*;
class Marker {
  public TreeNode constructMaximumBinaryTree(int[] nums) {
    return build(nums, 0, nums.length - 1);
  }

  private TreeNode build(int[] nums, int i, int j) {
    if (i > j)
      return null;

    int maxIndex = i;
    for (int k = i + 1; k <= j; ++k)
      if (nums[k] > nums[maxIndex])
        maxIndex = k;

    TreeNode root = new TreeNode(nums[maxIndex]);
    root.left = build(nums, i, maxIndex - 1);
    root.right = build(nums, maxIndex + 1, j);
    return root;
  }

    public boolean isCorrect(int[] nums, TreeNode output) {
        TreeNode expected = constructMaximumBinaryTree(nums);
        return sameTree(expected, output);
    }

    private static boolean sameTree(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        if (a.val != b.val) return false;
        return sameTree(a.left, b.left) && sameTree(a.right, b.right);
    }
}
