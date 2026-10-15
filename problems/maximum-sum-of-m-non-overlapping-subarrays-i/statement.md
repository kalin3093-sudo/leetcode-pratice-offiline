You are given an integer array `nums` of length `n`, and three integers `m`, `l`, and `r`.

Your task is to select **at least** one and **at most** `m` **non-overlapping subarrays** from `nums` such that:

-   Each selected **subarray** has a length between `[l, r]` (inclusive).
-   The total sum of all selected **subarrays** is **maximized**.

Return the **maximum** total sum you can achieve.

**Constraints:**

-   `1 ≤ n == nums.length ≤ 1000`
-   `-10^9 ≤ nums[i] ≤ 10^9`
-   `1 ≤ m ≤ n`
-   `1 ≤ l ≤ r ≤ n`
