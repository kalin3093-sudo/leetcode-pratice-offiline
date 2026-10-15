You are given a **0-indexed** integer array `nums`.

A **subsequence** of `nums` having length `k` and consisting of **indices** `i0 < i1 < ... < ik-1` is **balanced** if the following holds:

-   `nums[ij] - nums[ij-1] ≥ ij - ij-1`, for every `j` in the range `[1, k - 1]`.

A **subsequence** of `nums` having length `1` is considered balanced.

Return *an integer denoting the **maximum** possible **sum of elements** in a **balanced** subsequence of* `nums`.

A **subsequence** of an array is a new **non-empty** array that is formed from the original array by deleting some (**possibly none**) of the elements without disturbing the relative positions of the remaining elements.

**Constraints:**

-   `1 ≤ nums.length ≤ 10^5`
-   `-10^9 ≤ nums[i] ≤ 10^9`
