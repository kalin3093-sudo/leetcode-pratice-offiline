You are given an `m x n` integer matrix `mat` and an integer `k`. The matrix rows are 0-indexed.

The following process happens `k` times:

-   **Even-indexed** rows (0, 2, 4, ...) are cyclically shifted to the left.

![](https://assets.leetcode.com/uploads/2024/05/19/lshift.jpg)

-   **Odd-indexed** rows (1, 3, 5, ...) are cyclically shifted to the right.

![](https://assets.leetcode.com/uploads/2024/05/19/rshift-stlone.jpg)

Return `true` if the final modified matrix after `k` steps is identical to the original matrix, and `false` otherwise.

**Constraints:**

-   `1 ≤ mat.length ≤ 25`
-   `1 ≤ mat[i].length ≤ 25`
-   `1 ≤ mat[i][j] ≤ 25`
-   `1 ≤ k ≤ 50`
