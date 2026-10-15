You are given an integer `n` representing the number of tasks in a project, numbered from 0 to `n - 1`. These tasks are connected as a **tree** rooted at task 0. This is represented by a 2D integer array `edges` of length `n - 1`, where `edges[i] = [ui, vi]` indicates that task `ui` is the parent of task `vi`.

You are also given an array `baseTime` of length `n`, where `baseTime[i]` represents the time to complete task `i`.

The **finish time** of each task is calculated as follows:

-   Leaf task: The finish time is `baseTime[i]`.
-   Non-leaf task:
    -   Let `earliest` be the **minimum** finish time among its children, and `latest` be the **maximum** finish time among its children.
    -   Let `ownDuration` be `(latest - earliest) + baseTime[i]`.
    -   The finish time of task `i` is `latest + ownDuration`.

Return the finish time of the root task 0.

**Constraints:**

-   `1 ≤ n ≤ 10^5`
-   `edges.length = n - 1`
-   `edges[i] == [ui, vi]`
-   `0 ≤ ui, vi ≤ n - 1`
-   `ui != vi`
-   The input is generated such that `edges` represents a valid tree.
-   `baseTime.length == n`
-   `1 ≤ baseTime[i] ≤ 10^5`
-   The finish time of every task is guaranteed to be less than `2^53`.
