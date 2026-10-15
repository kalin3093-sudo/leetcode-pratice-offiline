A **peak** in an array `arr` is an element that is **greater** than its previous and next element in `arr`.

You are given an integer array `nums` and a 2D integer array `queries`.

You have to process queries of two types:

-   `queries[i] = [1, li, ri]`, determine the count of **peak** elements in the subarray `nums[li..ri]`.
-   `queries[i] = [2, indexi, vali]`, change `nums[indexi]` to `vali`.

Return an array `answer` containing the results of the queries of the first type in order.

**Notes:**

-   The **first** and the **last** element of an array or a subarray **cannot** be a peak.

**Constraints:**

-   `3 ≤ nums.length ≤ 10^5`
-   `1 ≤ nums[i] ≤ 10^5`
-   `1 ≤ queries.length ≤ 10^5`
-   `queries[i][0] == 1` or `queries[i][0] == 2`
-   For all `i` that:
    -   `queries[i][0] == 1`: `0 ≤ queries[i][1] ≤ queries[i][2] ≤ nums.length - 1`
    -   `queries[i][0] == 2`: `0 ≤ queries[i][1] ≤ nums.length - 1`, `1 ≤ queries[i][2] ≤ 10^5`
