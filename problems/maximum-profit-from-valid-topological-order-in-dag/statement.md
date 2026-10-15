You are given a **Directed Acyclic Graph (DAG)** with `n` nodes labeled from `0` to `n - 1`, represented by a 2D array `edges`, where `edges[i] = [ui, vi]` indicates a directed edge from node `ui` to `vi`. Each node has an associated **score** given in an array `score`, where `score[i]` represents the score of node `i`.

You must process the nodes in a **valid topological order**. Each node is assigned a **1-based position** in the processing order.

The **profit** is calculated by summing up the product of each node's score and its position in the ordering.

Return the **maximum** possible profit achievable with an optimal topological order.

A **topological order** of a DAG is a linear ordering of its nodes such that for every directed edge `u → v`, node `u` comes before `v` in the ordering.

**Constraints:**

-   `1 ≤ n == score.length ≤ 22`
-   `1 ≤ score[i] ≤ 10^5`
-   `0 ≤ edges.length ≤ n * (n - 1) / 2`
-   `edges[i] == [ui, vi]` denotes a directed edge from `ui` to `vi`.
-   `0 ≤ ui, vi < n`
-   `ui != vi`
-   The input graph is **guaranteed** to be a **DAG**.
-   There are no duplicate edges.
