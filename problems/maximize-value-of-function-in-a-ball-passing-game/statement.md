You are given an integer array `receiver` of length `n` and an integer `k`. `n` players are playing a ball-passing game.

You choose the starting player, `i`. The game proceeds as follows: player `i` passes the ball to player `receiver[i]`, who then passes it to `receiver[receiver[i]]`, and so on, for `k` passes in total. The game's score is the sum of the indices of the players who touched the ball, including repetitions, i.e. `i + receiver[i] + receiver[receiver[i]] + ... + receiver^(k)[i]`.

Return the **maximum** possible score.

**Notes:**

-   `receiver` may contain duplicates.
-   `receiver[i]` may be equal to `i`.

**Constraints:**

-   `1 ≤ receiver.length == n ≤ 10^5`
-   `0 ≤ receiver[i] ≤ n - 1`
-   `1 ≤ k ≤ 10^10`
