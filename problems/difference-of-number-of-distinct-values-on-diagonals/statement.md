Given a 2D `grid` of size `m x n`, you should find the matrix `answer` of size `m x n`.

The cell `answer[r][c]` is calculated by looking at the diagonal values of the cell `grid[r][c]`:

-   Let `leftAbove[r][c]` be the number of **distinct** values on the diagonal to the left and above the cell `grid[r][c]` not including the cell `grid[r][c]` itself.
-   Let `rightBelow[r][c]` be the number of **distinct** values on the diagonal to the right and below the cell `grid[r][c]`, not including the cell `grid[r][c]` itself.
-   Then `answer[r][c] = |leftAbove[r][c] - rightBelow[r][c]|`.

A **matrix diagonal** is a diagonal line of cells starting from some cell in either the topmost row or leftmost column and going in the bottom-right direction until the end of the matrix is reached.

-   For example, in the below diagram the diagonal is highlighted using the cell with indices `(2, 3)` colored gray:
    -   Red-colored cells are left and above the cell.
    -   Blue-colored cells are right and below the cell.

![](https://assets.leetcode.com/uploads/2024/05/26/diagonal.png)

Return the matrix `answer`.

**Constraints:**

-   `m == grid.length`
-   `n == grid[i].length`
-   `1 ≤ m, n, grid[i][j] ≤ 50`
