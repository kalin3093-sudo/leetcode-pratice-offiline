You are given a 2D integer matrix `mat` of size `m × n`, where:

-   `mat[r][c] == 1` means the cell at row `r` and column `c` is usable.
-   `mat[r][c] == 0` means it is not usable.

Your task is to find **two submatrices** that satisfy the following conditions:

-   Both submatrices must be squares of the same side length `k`.
-   The two submatrices must not share any cell.
-   Each submatrix can only cover cells where `mat[r][c] == 1`.

Return the **maximum possible area** of each of the two squares. If it is not possible to choose two such squares, return 0.

**Constraints:**

-   `mat.length == m`
-   `mat[i].length == n`
-   `1 ≤ m, n ≤ 500`
-   `mat[i][j]` is either 0 or 1.
