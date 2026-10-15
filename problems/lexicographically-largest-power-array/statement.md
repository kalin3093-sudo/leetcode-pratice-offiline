You are given an integer array `nums` of length `n`. You may rearrange its elements to form any permutation `perm`.

Define an array `power` of length 15. For each `0 ≤ i < 15`, `power[i]` is the largest integer `j`, where `0 ≤ j ≤ n`, such that the first `j` elements of `perm` all have the `(14 - i)^th` bit set.

Bit positions are indexed from right to left, starting with the `0^th` bit.

Return the lexicographically largest possible `power` array.

**Constraints:**

-   `1 ≤ nums.length ≤ 5 * 10^4`
-   `0 ≤ nums[i] < 2^15`
