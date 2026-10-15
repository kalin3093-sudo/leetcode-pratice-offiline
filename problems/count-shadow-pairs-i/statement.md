You are given an integer array `nums` of length `n`.

A pair of indices `(i, j)` is called a **shadow pair** if all of the following conditions are satisfied:

-   `0 ≤ i < j < n`
-   `nums[i] < nums[j]`
-   There **does not exist** an index `k` such that `i < k < j` and `nums[k] < nums[i] < nums[j]`.

Return the total number of **shadow pairs**.

**Constraints:**

-   `3 ≤ n == nums.length ≤ 10^5`
-   `1 ≤ nums[i] ≤ 10^9`
