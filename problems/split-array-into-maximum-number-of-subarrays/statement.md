You are given an array `nums` consisting of **non-negative** integers.

We define the score of subarray `nums[l..r]` such that `l ≤ r` as `nums[l] AND nums[l + 1] AND ... AND nums[r]` where **AND** is the bitwise `AND` operation.

Consider splitting the array into one or more subarrays such that the following conditions are satisfied:

-   **E****ach** element of the array belongs to **exactly** one subarray.
-   The sum of scores of the subarrays is the **minimum** possible.

Return *the **maximum** number of subarrays in a split that satisfies the conditions above.*

A **subarray** is a contiguous part of an array.

**Constraints:**

-   `1 ≤ nums.length ≤ 10^5`
-   `0 ≤ nums[i] ≤ 10^6`
