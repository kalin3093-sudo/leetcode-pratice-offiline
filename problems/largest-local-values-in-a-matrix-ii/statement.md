You are given an `n x m` integer matrix `matrix` containing non-negative integers.

A **non-zero** cell `(row, col)` checks the cells near it as follows:

-   Let `x = matrix[row][col]`.
-   Consider every cell within `x` rows and `x` columns of `(row, col)`.
-   Ignore cells that are outside the matrix.
-   Ignore the cells where both the row distance and column distance are exactly `x`.

The cell `(row, col)` is a **local maximum** if it is **non-zero** and no considered cell has a value **greater than** `x`.

Return an integer denoting the number of **local maximums** in `matrix`.

**Constraints:**

-   `1 ≤ n == matrix.length ≤ 200`
-   `1 ≤ m == matrix[i].length ≤ 200`
-   `0 ≤ matrix[i][j] ≤ 200`
