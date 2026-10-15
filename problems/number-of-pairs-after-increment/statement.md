You are given two integer arrays `nums1` and `nums2`, and a 2D integer array `queries`.

Each `queries[i]` is one of the following types:

-   `[1, x, y, val]` – **Add** `val` to every element in `nums2[x..y]`.
-   `[2, tot]` – **Compute** the number of pairs `(j, k)` such that `nums1[j] + nums2[k] == tot`.

Return an integer array `answer`, where `answer[j]` is the number of pairs for the `j^th` query of type 2.

**Constraints:**

-   `1 ≤ nums1.length ≤ 5`
-   `1 ≤ nums2.length ≤ 5 * 10^4`
-   `1 ≤ nums1[i], nums2[i] ≤ 10^5`
-   `1 ≤ queries.length ≤ 5 * 10^4`
-   `queries[i].length == 2 or 4`
    -   `queries[i] == [1, x, y, val], or`
    -   `queries[i] == [2, tot]`
    -   `0 ≤ x ≤ y < nums2.length`
    -   `1 ≤ val ≤ 10^5`
    -   `1 ≤ tot ≤ 10^9`
