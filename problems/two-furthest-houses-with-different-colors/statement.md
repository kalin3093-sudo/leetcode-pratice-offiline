There are `n` houses evenly lined up on the street, and each house is beautifully painted. You are given a **0-indexed** integer array `colors` of length `n`, where `colors[i]` represents the color of the `i^th` house.

Return *the **maximum** distance between **two** houses with **different** colors*.

The distance between the `i^th` and `j^th` houses is `abs(i - j)`, where `abs(x)` is the **absolute value** of `x`.

**Constraints:**

-   `n == colors.length`
-   `2 ≤ n ≤ 100`
-   `0 ≤ colors[i] ≤ 100`
-   Test data are generated such that **at least** two houses have different colors.
