You are given a **positive** integer array `nums`.

For a positive integer `k`, define `nonPositive(nums, k)` as the **minimum** number of **operations** needed to make every element of `nums` **non-positive**. In one operation, you can choose an index `i` and reduce `nums[i]` by `k`.

Return an integer denoting the **minimum** value of `k` such that `nonPositive(nums, k) ≤ k^2`.

**Constraints:**

-   `1 ≤ nums.length ≤ 10^5`
-   `1 ≤ nums[i] ≤ 10^5`
