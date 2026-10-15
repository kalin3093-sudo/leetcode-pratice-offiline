You are given an integer array `nums` of length `n`.

Alice and Bob are playing a game. Alice chooses:

-   An integer `k` such that `k > 1`.
-   Two integers `l` and `r` such that `0 ≤ l ≤ r < n`.

Initially, both Alice's and Bob's scores are 0.

For each index `i` in the range `[l, r]` (inclusive):

-   If `nums[i]` is divisible by `k`, Alice's score **increases** by `nums[i]`.
-   Otherwise, Bob's score **increases** by `nums[i]`.

The **score difference** is Alice's score **minus** Bob's score.

Alice wants to **maximize** the score difference. If there are multiple values of `k` that achieve the **maximum** score difference, she chooses the **smallest** such `k`.

Return the **product** of the **maximum** score difference and the chosen value of `k`. Since the result can be large, return it **modulo** `10^9 + 7`.

**Constraints:**

-   `1 ≤ nums.length ≤ 1000`
-   `1 ≤ nums[i] ≤ 10^6`
