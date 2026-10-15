You are given a **0-indexed** `m x n` integer matrix `grid`. Your initial position is at the **top-left** cell `(0, 0)`.

Starting from the cell `(i, j)`, you can move to one of the following cells:

-   Cells `(i, k)` with `j < k ≤ grid[i][j] + j` (rightward movement), or
-   Cells `(k, j)` with `i < k ≤ grid[i][j] + i` (downward movement).

Return *the minimum number of cells you need to visit to reach the **bottom-right** cell* `(m - 1, n - 1)`. If there is no valid path, return `-1`.

**Constraints:**

-   `m == grid.length`
-   `n == grid[i].length`
-   `1 ≤ m, n ≤ 10^5`
-   `1 ≤ m * n ≤ 10^5`
-   `0 ≤ grid[i][j] < m * n`
-   `grid[m - 1][n - 1] == 0`
