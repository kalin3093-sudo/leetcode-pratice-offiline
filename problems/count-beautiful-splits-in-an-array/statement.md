You are given an array `nums`.

A split of an array `nums` is **beautiful** if:

1.  The array `nums` is split into three subarrays: `nums1`, `nums2`, and `nums3`, such that `nums` can be formed by concatenating `nums1`, `nums2`, and `nums3` in that order.
2.  The subarray `nums1` is a prefix of `nums2` **OR** `nums2` is a prefix of `nums3`.

Return the **number of ways** you can make this split.

**Constraints:**

-   `1 ≤ nums.length ≤ 5000`
-   `0 ≤ nums[i] ≤ 50`
