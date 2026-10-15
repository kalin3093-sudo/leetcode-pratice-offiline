You are given an integer array `nums`, and an integer `k`. Let's introduce **K-or** operation by extending the standard bitwise OR. In K-or, a bit position in the result is set to `1` if at least `k` numbers in `nums` have a `1` in that position.

Return *the K-or of* `nums`.

**Constraints:**

-   `1 ≤ nums.length ≤ 50`
-   `0 ≤ nums[i] < 2^31`
-   `1 ≤ k ≤ nums.length`
