You are given two **0-indexed** integer arrays `nums` and `multipliers` of size `n` and `m` respectively, where `n ≥ m`.

You begin with a score of `0`. You want to perform **exactly** `m` operations. On the `i^th` operation (**0-indexed**) you will:

-   Choose one integer `x` from **either the start or the end** of the array `nums`.
-   Add `multipliers[i] * x` to your score.
    -   Note that `multipliers[0]` corresponds to the first operation, `multipliers[1]` to the second operation, and so on.
-   Remove `x` from `nums`.

Return *the **maximum** score after performing* `m` *operations.*

**Constraints:**

-   `n == nums.length`
-   `m == multipliers.length`
-   `1 ≤ m ≤ 300`
-   `m ≤ n ≤ 10^5`
-   `-1000 ≤ nums[i], multipliers[i] ≤ 1000`
