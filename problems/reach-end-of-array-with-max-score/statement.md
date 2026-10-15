You are given an integer array `nums` of length `n`.

Your goal is to start at index `0` and reach index `n - 1`. You can only jump to indices **greater** than your current index.

The score for a jump from index `i` to index `j` is calculated as `(j - i) * nums[i]`.

Return the **maximum** possible **total score** by the time you reach the last index.

**Constraints:**

-   `1 ≤ nums.length ≤ 10^5`
-   `1 ≤ nums[i] ≤ 10^5`
