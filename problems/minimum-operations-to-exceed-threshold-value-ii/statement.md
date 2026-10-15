You are given a **0-indexed** integer array `nums`, and an integer `k`.

You are allowed to perform some operations on `nums`, where in a single operation, you can:

-   Select the two **smallest** integers `x` and `y` from `nums`.
-   Remove `x` and `y` from `nums`.
-   Insert `(min(x, y) * 2 + max(x, y))` at any position in the array.

**Note** that you can only apply the described operation if `nums` contains **at least** two elements.

Return the **minimum** number of operations needed so that all elements of the array are **greater than or equal to** `k`.

**Constraints:**

-   `2 ≤ nums.length ≤ 2 * 10^5`
-   `1 ≤ nums[i] ≤ 10^9`
-   `1 ≤ k ≤ 10^9`
-   The input is generated such that an answer always exists. That is, after performing some number of operations, all elements of the array are greater than or equal to `k`.
