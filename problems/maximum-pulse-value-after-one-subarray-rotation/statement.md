You are given an integer array `nums` of length `n`.

Define the **pulse value** of an integer array `arr` as the **alternating sum** starting at index 0: `pulse(arr) = arr[0] - arr[1] + arr[2] - arr[3] + ...`.

You may perform **at most** one operation on `nums`:

-   Choose two indices `l` and `r` such that `0 ≤ l < r < n`.
-   **Left-rotate** the subarray `nums[l..r]` by **exactly** one position. For example, `[a, b, c, d]` becomes `[b, c, d, a]`.

Return the **maximum pulse value** that can be obtained after performing **at most** one such operation.

**Constraints:**

-   `1 ≤ n == nums.length ≤ 10^5`
-   `-10^9 ≤ nums[i] ≤ 10^9`
