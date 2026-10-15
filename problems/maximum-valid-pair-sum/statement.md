You are given an integer array `nums` of length `n` and an integer `k`.

A pair of indices `(i, j)` is called **valid** if:

-   `0 ≤ i < j < n`
-   `j - i ≥ k`

Return the **maximum** value of `nums[i] + nums[j]` among all valid pairs.

**Constraints:**

-   `2 ≤ n == nums.length ≤ 10^5`
-   `1 ≤ nums[i] ≤ 10^9`
-   `1 ≤ k ≤ n - 1`
