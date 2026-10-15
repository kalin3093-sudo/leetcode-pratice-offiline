You are given a **0-indexed** integer array `players`, where `players[i]` represents the **ability** of the `i^th` player. You are also given a **0-indexed** integer array `trainers`, where `trainers[j]` represents the **training capacity** of the `j^th` trainer.

The `i^th` player can **match** with the `j^th` trainer if the player's ability is **less than or equal to** the trainer's training capacity. Additionally, the `i^th` player can be matched with at most one trainer, and the `j^th` trainer can be matched with at most one player.

Return *the **maximum** number of matchings between* `players` *and* `trainers` *that satisfy these conditions.*

**Constraints:**

-   `1 ≤ players.length, trainers.length ≤ 10^5`
-   `1 ≤ players[i], trainers[j] ≤ 10^9`

**Note:** This question is the same as [445: Assign Cookies.](https://leetcode.com/problems/assign-cookies/description/)
