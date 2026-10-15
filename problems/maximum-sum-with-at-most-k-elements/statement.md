You are given a 2D integer matrix `grid` of size `n x m`, an integer array `limits` of length `n`, and an integer `k`. The task is to find the **maximum sum** of **at most** `k` elements from the matrix `grid` such that:

-   The number of elements taken from the `i^th` row of `grid` does not exceed `limits[i]`.
    

Return the **maximum sum**.

**Constraints:**

-   `n == grid.length == limits.length`
-   `m == grid[i].length`
-   `1 ≤ n, m ≤ 500`
-   `0 ≤ grid[i][j] ≤ 10^5`
-   `0 ≤ limits[i] ≤ m`
-   `0 ≤ k ≤ min(n * m, sum(limits))`
