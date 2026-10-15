Given an integer array `num` sorted in non-decreasing order.

You can perform the following operation any number of times:

-   Choose **two** indices, `i` and `j`, where `nums[i] < nums[j]`.
-   Then, remove the elements at indices `i` and `j` from `nums`. The remaining elements retain their original order, and the array is re-indexed.

Return the **minimum** length of `nums` after applying the operation zero or more times.

**Constraints:**

-   `1 ≤ nums.length ≤ 10^5`
-   `1 ≤ nums[i] ≤ 10^9`
-   `nums` is sorted in **non-decreasing** order.
