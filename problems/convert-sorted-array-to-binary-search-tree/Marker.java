import java.util.*;
class Marker {
  public TreeNode sortedArrayToBST(int[] nums) {
    return build(nums, 0, nums.length - 1);
  }

  private TreeNode build(int[] nums, int l, int r) {
    if (l > r)
      return null;
    final int m = (l + r) / 2;
    return new TreeNode(nums[m], build(nums, l, m - 1), build(nums, m + 1, r));
  }

    public boolean isCorrect(int[] nums, TreeNode output) {
        if (output == null) return false;
        // inorder traversal must reproduce the sorted input
        List<Integer> inorder = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = output;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            inorder.add(cur.val);
            cur = cur.right;
        }
        if (inorder.size() != nums.length) return false;
        for (int i = 0; i < nums.length; i++)
            if (inorder.get(i) != nums[i]) return false;
        return isBST(output, Long.MIN_VALUE, Long.MAX_VALUE) && isBalanced(output) >= 0;
    }

    private boolean isBST(TreeNode node, long lo, long hi) {
        if (node == null) return true;
        if (node.val <= lo || node.val >= hi) return false;
        return isBST(node.left, lo, node.val) && isBST(node.right, node.val, hi);
    }

    // returns height, or -1 if unbalanced
    private int isBalanced(TreeNode node) {
        if (node == null) return 0;
        int l = isBalanced(node.left);
        int r = isBalanced(node.right);
        if (l < 0 || r < 0 || Math.abs(l - r) > 1) return -1;
        return Math.max(l, r) + 1;
    }
}
