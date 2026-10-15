You are given an integer `n` representing the number of players in a game and a 2D array `pick` where `pick[i] = [xi, yi]` represents that the player `xi` picked a ball of color `yi`.

Player `i` **wins** the game if they pick **strictly more** than `i` balls of the **same** color. In other words,

-   Player 0 wins if they pick any ball.
-   Player 1 wins if they pick at least two balls of the *same* color.
-   ...
-   Player `i` wins if they pick at least `i + 1` balls of the *same* color.

Return the number of players who **win** the game.

**Note** that *multiple* players can win the game.

**Constraints:**

-   `2 ≤ n ≤ 10`
-   `1 ≤ pick.length ≤ 100`
-   `pick[i].length == 2`
-   `0 ≤ xi ≤ n - 1`
-   `0 ≤ yi ≤ 10`
