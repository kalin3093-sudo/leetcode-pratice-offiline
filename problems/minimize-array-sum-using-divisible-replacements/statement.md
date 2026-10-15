You are given an integer array `nums`.

You can perform the following operation any number of times:

-   Choose two indices `a` and `b` such that `nums[a] % nums[b] == 0`.
-   Replace `nums[a]` with `nums[b]`.

Return the **minimum** possible sum of the array after performing any number of operations.

**Constraints:**

-   `1 ≤ nums.length ≤ 10^5`
-   `1 ≤ nums[i] ≤ 10^5`
