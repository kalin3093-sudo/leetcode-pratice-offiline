You are given an integer array `nums` of length `n`.

The **score** of an index `i` is defined as the number of indices `j` such that:

-   `i < j < n`, and
-   `nums[i]` and `nums[j]` have different parity (one is even and the other is odd).

Return an integer array `answer` of length `n`, where `answer[i]` is the score of index `i`.

**Constraints:**

-   `1 ≤ nums.length ≤ 100`
-   `1 ≤ nums[i] ≤ 100`
