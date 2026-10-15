You are given a **0-indexed** string `word` of length `n` consisting of digits, and a positive integer `m`.

The **divisibility array** `div` of `word` is an integer array of length `n` such that:

-   `div[i] = 1` if the **numeric value** of `word[0,...,i]` is divisible by `m`, or
-   `div[i] = 0` otherwise.

Return *the divisibility array of* `word`.

**Constraints:**

-   `1 ≤ n ≤ 10^5`
-   `word.length == n`
-   `word` consists of digits from `0` to `9`
-   `1 ≤ m ≤ 10^9`
