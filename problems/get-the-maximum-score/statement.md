You are given two **sorted** arrays of distinct integers `nums1` and `nums2`.

A **valid path** is defined as follows:

-   Choose array `nums1` or `nums2` to traverse (from index-0).
-   Traverse the current array from left to right.
-   If you are reading any value that is present in `nums1` and `nums2` you are allowed to change your path to the other array. (Only one repeated value is considered in the valid path).

The **score** is defined as the sum of unique values in a valid path.

Return *the maximum score you can obtain of all possible **valid paths***. Since the answer may be too large, return it modulo `10^9 + 7`.

**Constraints:**

-   `1 ≤ nums1.length, nums2.length ≤ 10^5`
-   `1 ≤ nums1[i], nums2[i] ≤ 10^7`
-   `nums1` and `nums2` are strictly increasing.
