You are given an `m x n` integer matrix `grid`.

Two players move across the grid:

-   Player 1 starts at the top-left cell `(0, 0)` and can move only right or down. Their destination is the bottom-right cell `(m - 1, n - 1)`.
-   Player 2 starts at the bottom-left cell `(m - 1, 0)` and can move only right or up. Their destination is the top-right cell `(0, n - 1)`.

Each player must choose a valid path from their respective starting cell to their destination.

A cell is called **shared** if it belongs to **both** chosen paths.

Return an integer denoting the **maximum** possible sum of values of all **shared** cells.

**Constraints:**

-   `m == grid.length`
-   `n == grid[i].length`
-   `2 ≤ m, n ≤ 1000`
-   `4 ≤ m * n ≤ 5 * 10^5`
-   `-100 ≤ grid[i][j] ≤ 100`
