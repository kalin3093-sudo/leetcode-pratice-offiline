You are given a 2D integer array `grid` of size `m x n`, where `grid[i][j]` represents the cost of visiting cell `(i, j)`, and an integer `k`.

You start at the **top-left** cell `(0, 0)` and want to reach the **bottom-right** cell `(m - 1, n - 1)`.

From each cell, you may move one step in any of the four directions: **up**, **down**, **left**, or **right**.

The cost of a path is the sum of the values of all visited cells, **including** the starting and ending cells. If a cell is visited more than once, its value is included each time it is visited.

Return the **minimum** possible path cost to reach `(m - 1, n - 1)` using **at most** `k` turns. If no such path exists, return `-1`.

A **turn** occurs when the direction changes between two consecutive moves. For example, moving right and then down counts as one turn, while moving right and then right does not.

**Constraints:**

-   `1 ≤ m == grid.length ≤ 75`
-   `1 ≤ n == grid[i].length ≤ 75`
-   `0 ≤ grid[i][j] ≤ 1000`
-   `0 ≤ k < min(m, n)`
