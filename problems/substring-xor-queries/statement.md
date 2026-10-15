You are given a **binary string** `s`, and a **2D** integer array `queries` where `queries[i] = [firsti, secondi]`.

For the `i^th` query, find the **shortest substring** of `s` whose **decimal value**, `val`, yields `secondi` when **bitwise XORed** with `firsti`. In other words, `val ^ firsti == secondi`.

The answer to the `i^th` query is the endpoints (**0-indexed**) of the substring `[lefti, righti]` or `[-1, -1]` if no such substring exists. If there are multiple answers, choose the one with the **minimum** `lefti`.

*Return an array* `ans` *where* `ans[i] = [lefti, righti]` *is the answer to the* `i^th` *query.*

A **substring** is a contiguous non-empty sequence of characters within a string.

**Constraints:**

-   `1 ≤ s.length ≤ 10^4`
-   `s[i]` is either `'0'` or `'1'`.
-   `1 ≤ queries.length ≤ 10^5`
-   `0 ≤ firsti, secondi ≤ 10^9`
