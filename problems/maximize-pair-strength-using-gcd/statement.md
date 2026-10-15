You are given an integer array `nums`.

Choose exactly one pair of distinct indices `i` and `j`. The strength of the pair is defined as `(nums[i] * nums[j]) / gcd(nums[i], nums[j])^2`.

Return the maximum strength over all possible pairs.

**Constraints:**

-   `2 ≤ nums.length ≤ 2000`
-   `1 ≤ nums[i] ≤ 10^5`
