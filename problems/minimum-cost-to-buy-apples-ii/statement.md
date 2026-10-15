You are given an integer `n` and an integer array `prices` of length `n`, where `prices[i]` is the price of apples at shop `i`.

You are also given a 2D integer array `roads`, where `roads[i] = [ui, vi, costi, taxi]` represents a **bidirectional** road:

-   `ui` and `vi` are the shops connected by the road.
-   `costi` is the cost to travel the road **without** carrying apples.
-   `taxi` is the multiplier applied to `costi` when traveling **with** apples.

For each shop `i`, you can either:

-   Buy apples locally at shop `i` for `prices[i]`.
-   Travel **empty** to any shop `j` using **any** number of roads, buy apples for `prices[j]`, and return to shop `i` while carrying apples, paying `cost * tax` on each road used for the return trip.

The forward path, where you travel empty, and the return path may be **different**.

Return an integer array `ans` of length `n`, where `ans[i]` is the **minimum** total cost to buy apples starting from shop `i`.

**Constraints:**

-   `1 ≤ n ≤ 1000`
-   `prices.length == n`
-   `1 ≤ prices[i] ≤ 10^9`
-   `0 ≤ roads.length ≤ min(n × (n - 1) / 2, 2000)`
-   `roads[i] = [ui, vi, costi, taxi]`
-   `0 ≤ ui, vi ≤ n - 1`
-   `ui != vi`
-   `1 ≤ costi ≤ 10^9`
-   `1 ≤ taxi ≤ 100`
-   There are no **repeated** edges.
