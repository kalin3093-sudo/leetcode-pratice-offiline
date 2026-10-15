You are given two **0-indexed** integer arrays `nums1` and `nums2`, each of length `n`, and a **1-indexed 2D array** `queries` where `queries[i] = [xi, yi]`.

For the `i^th` query, find the **maximum value** of `nums1[j] + nums2[j]` among all indices `j` `(0 ≤ j < n)`, where `nums1[j] ≥ xi` and `nums2[j] ≥ yi`, or **\-1** if there is no `j` satisfying the constraints.

Return *an array* `answer` *where* `answer[i]` *is the answer to the* `i^th` *query.*

**Constraints:**

-   `nums1.length == nums2.length` 
-   `n == nums1.length` 
-   `1 ≤ n ≤ 10^5`
-   `1 ≤ nums1[i], nums2[i] ≤ 10^9` 
-   `1 ≤ queries.length ≤ 10^5`
-   `queries[i].length == 2`
-   `xi == queries[i][1]`
-   `yi == queries[i][2]`
-   `1 ≤ xi, yi ≤ 10^9`
