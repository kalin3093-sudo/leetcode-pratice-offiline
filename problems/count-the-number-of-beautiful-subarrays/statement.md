You are given a **0-indexed** integer array `nums`. In one operation, you can:

-   Choose two different indices `i` and `j` such that `0 ≤ i, j < nums.length`.
-   Choose a non-negative integer `k` such that the `k^th` bit (**0-indexed**) in the binary representation of `nums[i]` and `nums[j]` is `1`.
-   Subtract `2^k` from `nums[i]` and `nums[j]`.

A subarray is **beautiful** if it is possible to make all of its elements equal to `0` after applying the above operation any number of times (including zero).

Return *the number of **beautiful subarrays** in the array* `nums`.

A subarray is a contiguous **non-empty** sequence of elements within an array.

**Note**: Subarrays where all elements are initially 0 are considered beautiful, as no operation is needed.

**Constraints:**

-   `1 ≤ nums.length ≤ 10^5`
-   `0 ≤ nums[i] ≤ 10^6`
