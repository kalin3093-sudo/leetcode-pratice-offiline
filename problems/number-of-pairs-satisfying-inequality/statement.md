You are given two **0-indexed** integer arrays `nums1` and `nums2`, each of size `n`, and an integer `diff`. Find the number of **pairs** `(i, j)` such that:

-   `0 ≤ i < j ≤ n - 1` **and**
-   `nums1[i] - nums1[j] ≤ nums2[i] - nums2[j] + diff`.

Return *the **number of pairs** that satisfy the conditions.*

**Constraints:**

-   `n == nums1.length == nums2.length`
-   `2 ≤ n ≤ 10^5`
-   `-10^4 ≤ nums1[i], nums2[i] ≤ 10^4`
-   `-10^4 ≤ diff ≤ 10^4`
