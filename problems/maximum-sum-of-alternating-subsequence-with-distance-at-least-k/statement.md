You are given an integer array `nums` of length `n` and an integer `k`.

Pick a **subsequence** with indices `0 ≤ i1 < i2 < ... < im < n` such that:

-   For every `1 ≤ t < m`, `it+1 - it ≥ k`.
-   The selected values form a **strictly alternating** sequence. In other words, either:
    -   `nums[i1] < nums[i2] > nums[i3] < ...`, or
    -   `nums[i1] > nums[i2] < nums[i3] > ...`

A **subsequence** of length 1 is also considered **strictly** alternating. The score of a **valid** subsequence is the **sum** of its selected values.

Return an integer denoting the **maximum** possible **score** of a valid subsequence.

**Constraints:**

-   `1 ≤ n == nums.length ≤ 10^5`
-   `1 ≤ nums[i] ≤ 10^5`
-   `1 ≤ k ≤ n`
