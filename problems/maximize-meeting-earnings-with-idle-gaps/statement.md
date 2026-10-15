You are given a 2D integer array `meetings`, where `meetings[i] = [starti, endi, revenuei]` represents a meeting starting at time `starti`, ending at time `endi`, with revenue `revenuei`.

All meetings use **half-open intervals** `[start, end)`, so meetings that only touch at endpoints do **not** overlap.

You may select any **non-empty subset** of meetings such that no two selected meetings overlap. You earn the revenue of each selected meeting.

Arrange the selected meetings in **increasing order of their start times**. For each pair of adjacent meetings in this order, you also earn 1 unit of revenue per unit of idle time between them. This idle time equals the later meeting's start time minus the earlier meeting's end time.

No idle revenue is earned before the earliest selected meeting starts or after the latest selected meeting ends. If only one meeting is selected, no idle revenue is earned.

Return the **maximum total earnings** achievable.

A **subset** of an array is a selection of elements of the array.

**Constraints**

-   `1 ≤ meetings.length ≤ 10^5`
-   `meetings[i] = [starti, endi, revenuei]`
-   `0 ≤ starti < endi ≤ 10^9`
-   `1 ≤ revenuei ≤ 10^9`
