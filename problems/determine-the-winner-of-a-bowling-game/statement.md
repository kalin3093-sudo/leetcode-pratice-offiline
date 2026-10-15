You are given two **0-indexed** integer arrays `player1` and `player2`, representing the number of pins that player 1 and player 2 hit in a bowling game, respectively.

The bowling game consists of `n` turns, and the number of pins in each turn is exactly 10.

Assume a player hits `xi` pins in the i^th turn. The value of the i^th turn for the player is:

-   `2xi` if the player hits 10 pins **in either (i - 1)^th or (i - 2)^th turn**.
-   Otherwise, it is `xi`.

The **score** of the player is the sum of the values of their `n` turns.

Return

-   1 if the score of player 1 is more than the score of player 2,
-   2 if the score of player 2 is more than the score of player 1, and
-   0 in case of a draw.

**Constraints:**

-   `n == player1.length == player2.length`
-   `1 ≤ n ≤ 1000`
-   `0 ≤ player1[i], player2[i] ≤ 10`
