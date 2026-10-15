You are given an integer `n`, a 2D integer array `restrictions`, and an integer array `diff` of length `n - 1`. Your task is to construct a sequence of length `n`, denoted by `a[0], a[1], ..., a[n - 1]`, such that it satisfies the following conditions:

-   `a[0]` is 0.
-   All elements in the sequence are **non-negative**.
-   For every index `i` (`0 ≤ i ≤ n - 2`), `abs(a[i] - a[i + 1]) ≤ diff[i]`.
-   For each `restrictions[i] = [idx, maxVal]`, the value at position `idx` in the sequence must not exceed `maxVal` (i.e., `a[idx] ≤ maxVal`).

Your goal is to construct a valid sequence that **maximizes** the **largest** value within the sequence while satisfying all the above conditions.

Return an integer denoting the **largest** value present in such an optimal sequence.

**Constraints:**

-   `2 ≤ n ≤ 10^5`
-   `1 ≤ restrictions.length ≤ n - 1`
-   `restrictions[i].length == 2`
-   `restrictions[i] = [idx, maxVal]`
-   `1 ≤ idx < n`
-   `1 ≤ maxVal ≤ 10^6`
-   `diff.length == n - 1`
-   `1 ≤ diff[i] ≤ 10`
-   The values of `restrictions[i][0]` are unique.
