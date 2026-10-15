You are given an integer array `nums` where `nums` is **strictly increasing**.

For each index `x`, let `closest(x)` be the **adjacent** index `y` such that `abs(nums[x] - nums[y])` is **minimized**. If both **adjacent** indices exist and give the same difference, choose the **smaller** index.

From any index `x`, you can move in two ways:

-   To any index `y` with cost `abs(nums[x] - nums[y])`, or
-   To `closest(x)` with cost 1.

You are also given a 2D integer array `queries`, where each `queries[i] = [li, ri]`.

For each query, calculate the **minimum total cost** to move from index `li` to index `ri`.

Return an integer array `ans`, where `ans[i]` is the answer for the `i^th` query.

The **absolute difference** between two values `x` and `y` is defined as `abs(x - y)`.

**Constraints:**

-   `2 ≤ nums.length ≤ 10^5`
-   `-10^9 ≤ nums[i] ≤ 10^9`
-   `nums` is strictly increasing
-   `1 ≤ queries.length ≤ 10^5`
-   `queries[i] = [li, ri]`
-   `0 ≤ li, ri < nums.length`
