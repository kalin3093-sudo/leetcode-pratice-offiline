You are given an integer array `nums` and a **non-negative** integer `k`. A sequence of integers `seq` is called **good** if there are **at most** `k` indices `i` in the range `[0, seq.length - 2]` such that `seq[i] != seq[i + 1]`.

Return the **maximum** possible length of a **good** subsequence of `nums`.

**Constraints:**

-   `1 ≤ nums.length ≤ 5 * 10^3`
-   `1 ≤ nums[i] ≤ 10^9`
-   `0 ≤ k ≤ min(50, nums.length)`
