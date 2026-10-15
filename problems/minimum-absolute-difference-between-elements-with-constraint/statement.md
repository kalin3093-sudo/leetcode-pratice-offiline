You are given a **0-indexed** integer array `nums` and an integer `x`.

Find the **minimum absolute difference** between two elements in the array that are at least `x` indices apart.

In other words, find two indices `i` and `j` such that `abs(i - j) ≥ x` and `abs(nums[i] - nums[j])` is minimized.

Return *an integer denoting the **minimum** absolute difference between two elements that are at least* `x` *indices apart*.

**Constraints:**

-   `1 ≤ nums.length ≤ 10^5`
-   `1 ≤ nums[i] ≤ 10^9`
-   `0 ≤ x < nums.length`
