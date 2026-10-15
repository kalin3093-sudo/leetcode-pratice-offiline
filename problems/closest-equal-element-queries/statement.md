You are given a **circular** array `nums` and an array `queries`.

For each query `i`, you have to find the following:

-   The **minimum** distance between the element at index `queries[i]` and **any** other index `j` in the **circular** array, where `nums[j] == nums[queries[i]]`. If no such index exists, the answer for that query should be -1.

Return an array `answer` of the **same** size as `queries`, where `answer[i]` represents the result for query `i`.

**Constraints:**

-   `1 ≤ queries.length ≤ nums.length ≤ 10^5`
-   `1 ≤ nums[i] ≤ 10^6`
-   `0 ≤ queries[i] < nums.length`
