You are given two integer arrays `nums1` and `nums2` of size `n`.

You can perform the following two operations any number of times on these two arrays:

-   **Swap within the same array**: Choose two indices `i` and `j`. Then, choose either to swap `nums1[i]` and `nums1[j]`, or `nums2[i]` and `nums2[j]`. This operation is **free of charge**.
-   **Swap between two arrays**: Choose an index `i`. Then, swap `nums1[i]` and `nums2[i]`. This operation **incurs a cost of 1**.

Return an integer denoting the **minimum cost** to make `nums1` and `nums2` **identical**. If this is not possible, return -1.

**Constraints:**

-   `2 ≤ n == nums1.length == nums2.length ≤ 8 * 10^4`
-   `1 ≤ nums1[i], nums2[i] ≤ 8 * 10^4`
