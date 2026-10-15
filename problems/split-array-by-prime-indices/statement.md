You are given an integer array `nums`.

Split `nums` into two arrays `A` and `B` using the following rule:

-   Elements at **prime** indices in `nums` must go into array `A`.
-   All other elements must go into array `B`.

Return the **absolute** difference between the sums of the two arrays: `|sum(A) - sum(B)|`.

**Note:** An empty array has a sum of 0.

**Constraints:**

-   `1 ≤ nums.length ≤ 10^5`
-   `-10^9 ≤ nums[i] ≤ 10^9`
