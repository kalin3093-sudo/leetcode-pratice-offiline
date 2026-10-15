You are given an integer array `nums` of length `n`.

In one operation, you may choose any **subarray** `nums[l..r]` and **increase** each element in that **subarray** by `x`, where `x` is any **positive** integer.

Return the **minimum** possible **sum** of the values of `x` across all operations required to make the array **non-decreasing**.

An array is **non-decreasing** if `nums[i] ≤ nums[i + 1]` for all `0 ≤ i < n - 1`.

**Constraints:**

-   `1 ≤ n == nums.length ≤ 10^5`
-   `1 ≤ nums[i] ≤ 10^9`
