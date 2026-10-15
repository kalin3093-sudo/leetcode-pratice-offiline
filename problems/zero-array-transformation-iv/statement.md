You are given an integer array `nums` of length `n` and a 2D array `queries`, where `queries[i] = [li, ri, vali]`.

Each `queries[i]` represents the following action on `nums`:

-   Select a subset of indices in the range `[li, ri]` from `nums`.
-   Decrement the value at each selected index by **exactly** `vali`.

A **Zero Array** is an array with all its elements equal to 0.

Return the **minimum** possible **non-negative** value of `k`, such that after processing the first `k` queries in **sequence**, `nums` becomes a **Zero Array**. If no such `k` exists, return -1.

**Constraints:**

-   `1 ≤ nums.length ≤ 10`
-   `0 ≤ nums[i] ≤ 1000`
-   `1 ≤ queries.length ≤ 1000`
-   `queries[i] = [li, ri, vali]`
-   `0 ≤ li ≤ ri < nums.length`
-   `1 ≤ vali ≤ 10`
