You are given an integer array `monsters`, where `monsters[i]` represents the strength of the `i^th` monster.

You are also given a 2D integer array `boosts`, where `boosts[i] = [li, ri, vi]` indicates that `vi` is added to your **temporary bonus** while fighting any monster whose index lies in `[li, ri]`. Boost ranges may overlap, and the values of all applicable boosts are added together.

You start with a **non-negative** initial strength and fight the monsters from left to right.

For each monster at index `i`:

-   Let `bonus` be the **sum** of the values of all boosts that apply to monster `i`.
-   You can defeat the monster only if your current strength plus `bonus` is **at least** `monsters[i]`.
-   After defeating the monster, only your current strength decreases by `monsters[i]`. If it becomes **negative**, it is set to 0.

Return the **minimum** initial strength required to defeat all monsters.

Note: The temporary bonus is used only to determine whether the current monster can be defeated. It does not otherwise change your current strength.

**Constraints:**

-   `1 ≤ monsters.length ≤ 5 * 10^4`
-   `1 ≤ monsters[i] ≤ 10^9`
-   `0 ≤ boosts.length ≤ 5 * 10^4`
-   `boosts[i] == [li, ri, vi]`
-   `0 ≤ li ≤ ri < monsters.length`
-   `1 ≤ vi ≤ 10^9`
