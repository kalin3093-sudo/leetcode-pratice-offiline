You are given an integer `n` and a **directed** graph with `n` nodes labeled from 0 to `n - 1`. This is represented by a 2D array `edges`, where `edges[i] = [ui, vi, starti, endi]` indicates an edge from node `ui` to `vi` that can **only** be used at any integer time `t` such that `starti ≤ t ≤ endi`.

You start at node 0 at time 0.

In one unit of time, you can either:

-   Wait at your current node without moving, or
-   Travel along an outgoing edge from your current node if the current time `t` satisfies `starti ≤ t ≤ endi`.

Return the **minimum** time required to reach node `n - 1`. If it is impossible, return `-1`.

**Constraints:**

-   `1 ≤ n ≤ 10^5`
-   `0 ≤ edges.length ≤ 10^5`
-   `edges[i] == [ui, vi, starti, endi]`
-   `0 ≤ ui, vi ≤ n - 1`
-   `ui != vi`
-   `0 ≤ starti ≤ endi ≤ 10^9`
