You are given a **0-indexed** integer array `nums` containing `n` **distinct** positive integers. A permutation of `nums` is called special if:

-   For all indexes `0 ≤ i < n - 1`, either `nums[i] % nums[i+1] == 0` or `nums[i+1] % nums[i] == 0`.

Return *the total number of special permutations.* As the answer could be large, return it **modulo** `10^9 + 7`.

**Constraints:**

-   `2 ≤ nums.length ≤ 14`
-   `1 ≤ nums[i] ≤ 10^9`
