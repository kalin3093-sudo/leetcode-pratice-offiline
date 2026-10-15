You are given two integer arrays `nums1` and `nums0`, each of size `n`.

-   `nums1[i]` represents the number of `'1'`s in the `i^th` segment.
-   `nums0[i]` represents the number of `'0'`s in the `i^th` segment.

For each index `i`, construct a binary segment consisting of:

-   `nums1[i]` occurrences of `'1'` followed by
-   `nums0[i]` occurrences of `'0'`.

You may **rearrange** the order of these **segments** in any way. After rearranging, **concatenate** all segments to form a single binary string.

Return the **maximum** possible integer value of the concatenated binary string.

Since the result can be very large, return the answer **modulo** `10^9 + 7`.

**Constraints:**

-   `1 ≤ n == nums1.length == nums0.length ≤ 10^5`
-   `0 ≤ nums1[i], nums0[i] ≤ 10^4`
-   `nums1[i] + nums0[i] > 0`
-   The total sum of all elements in `nums1` and `nums0` does not exceed 2 \* 10^5.
