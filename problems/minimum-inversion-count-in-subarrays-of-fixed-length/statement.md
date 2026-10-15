You are given an integer array `nums` of length `n` and an integer `k`.

An **inversion** is a pair of indices `(i, j)` from `nums` such that `i < j` and `nums[i] > nums[j]`.

The **inversion count** of a **subarray** is the number of inversions within it.

Return the **minimum** inversion count among all **subarrays** of `nums` with length `k`.

**Constraints:**

-   `1 ≤ n == nums.length ≤ 10^5`
-   `1 ≤ nums[i] ≤ 10^9`
-   `1 ≤ k ≤ n`
