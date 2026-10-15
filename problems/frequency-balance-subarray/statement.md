You are given an integer array `nums`.

Define a **frequency balance subarray** as follows:

-   If the subarray contains only one distinct value, it is frequency balanced.
-   Otherwise, there must exist a positive integer `f` such that every distinct value in the subarray occurs either `f` or `2 * f` times, and both frequencies occur among the distinct values.

Return an integer denoting the length of the **longest** frequency balance subarray.

**Constraints:**

-   `1 ≤ nums.length ≤ 10^3`
-   `1 ≤ nums[i] ≤ 10^9`
