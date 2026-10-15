You are given two integer arrays `nums1` and `nums2`.

We say that `nums2` is reachable from `nums1` with an integer `x` if there exist two elements in `nums1` that, when removed, and `x` is added to all the remaining elements of `nums1` (or subtracted in the case of a negative `x`), the resulting array becomes equal to `nums2`. Two arrays are considered **equal** when they contain the same integers with the same frequencies.

Return the **minimum** possible integer `x` that makes `nums2` reachable from `nums1`.

It is guaranteed that `nums2` is reachable from `nums1` with at least one `x`.

**Constraints:**

-   `3 ≤ nums1.length ≤ 200`
-   `nums2.length == nums1.length - 2`
-   `0 ≤ nums1[i], nums2[i] ≤ 1000`
-   It is guaranteed that `nums2` is reachable from `nums1` with at least one `x`.
