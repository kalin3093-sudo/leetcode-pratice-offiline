You are given an integer array `nums`.

A **subarray** `nums[l..r]` is valid if there are no three **distinct** indices `i`, `j`, and `k` such that `l ≤ i, j, k ≤ r` and:

-   `nums[i] + nums[j] == nums[k]`

Return the **maximum** length of a valid subarray of `nums`.

A **subarray** is a contiguous **non-empty** sequence of elements within an array.

**Constraints:**

-   `1 ≤ nums.length ≤ 1000`
-   `1 ≤ nums[i] ≤ 500`
