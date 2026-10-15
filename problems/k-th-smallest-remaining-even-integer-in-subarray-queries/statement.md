You are given an integer array `nums` where `nums` is **strictly increasing**.

You are also given a 2D integer array `queries`, where `queries[i] = [li, ri, ki]`.

For each query `[li, ri, ki]`:

-   Consider the **subarray** `nums[li..ri]`
-   From the **infinite** sequence of all **positive even integers**: `2, 4, 6, 8, 10, 12, 14, ...`
-   **Remove** all elements that appear in the **subarray** `nums[li..ri]`.
-   Find the `ki^th` **smallest integer** remaining in the sequence after the removals.

Return an integer array `ans`, where `ans[i]` is the result for the `i^th` query.

**Constraints:**

-   `1 ≤ nums.length ≤ 10^5`
-   `1 ≤ nums[i] ≤ 10^9`
-   `nums` is strictly increasing
-   `1 ≤ queries.length ≤ 10^5`
-   `queries[i] = [li, ri, ki]`
-   `0 ≤ li ≤ ri < nums.length`
-   `1 ≤ ki ≤ 10^9`
