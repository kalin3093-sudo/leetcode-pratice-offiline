You are given two strings `skill` and `station` of lengths `n` and `m`, respectively.

`skill[i]` represents the skill of worker `i`, and `station[j]` represents the skill supported by station `j`.

You must assign **every** worker to a **distinct** station. Let `ji` be the index of the station assigned to worker `i`. A valid assignment must satisfy:

-   `station[ji] == skill[i]` for every `0 ≤ i < n`.
-   The assigned station indices must be **strictly** increasing in worker order, meaning `j0 < j1 < ... < jn - 1`.

The **gap** of an assignment is the **maximum difference** between the station indices assigned to two **consecutive** workers. In other words, it is `max(ji - ji - 1)` over all `1 ≤ i < n`.

If there is only one worker, the gap is 0.

Return the **maximum** possible gap among all valid assignments. It is guaranteed that **at least** one valid assignment exists.

**Constraints:**

-   `skill.length == n`
-   `station.length == m`
-   `1 ≤ n ≤ m ≤ 10^5`
-   `skill` and `station` consist of lowercase English letters.
-   It is guaranteed that a valid assignment exists for every worker.
