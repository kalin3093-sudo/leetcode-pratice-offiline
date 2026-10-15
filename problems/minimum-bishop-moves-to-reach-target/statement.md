There is an `8 x 8` empty chessboard with **1-indexed** rows and columns.

You are given an array `source = [sr, sc]` representing the starting position of a **bishop**, and an array `target = [tr, tc]` representing the target position.

In one move, the bishop travels one or more squares along a single **diagonal** direction, staying within the board.

Return the **minimum** number of moves for the bishop to land **exactly** on `target`. If it can never reach `target`, return -1.

**Constraints:**

-   `source.length == target.length == 2`
-   `1 ≤ sr, sc, tr, tc ≤ 8`
-   `source != target`
