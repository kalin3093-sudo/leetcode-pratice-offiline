You are given an integer array `nums` and an integer `k`.

A subarray is **valid** if its sum is divisible by `k`, or can become divisible by `k` by **negating one element within that subarray**.

Negating an element means replacing its value `x` with `-x`.

Return the **length of the longest valid subarray**. If no valid subarray exists, return 0.

A **subarray** is a contiguous, non-empty sequence of elements within an array.

**Constraints:**

-   `1 ≤ nums.length ≤ 10^5`
-   `-10^5 ≤ nums[i] ≤ 10^5`
-   `1 ≤ k ≤ 3000`
