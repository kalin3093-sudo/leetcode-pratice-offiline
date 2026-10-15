You are given a **circular** array `balance` of length `n`, where `balance[i]` is the net balance of person `i`.

In one move, a person can transfer **exactly** 1 unit of balance to either their left or right neighbor.

Return the **minimum** number of moves required so that every person has a **non-negative** balance. If it is impossible, return `-1`.

**Note**: You are guaranteed that **at most** 1 index has a **negative** balance initially.

**Constraints:**

-   `1 ≤ n == balance.length ≤ 10^5`
-   `-10^9 ≤ balance[i] ≤ 10^9`
-   There is at most one negative value in `balance` initially.
