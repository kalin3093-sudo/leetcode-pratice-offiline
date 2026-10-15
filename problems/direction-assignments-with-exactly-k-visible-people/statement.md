You are given three integers `n`, `pos`, and `k`.

There are `n` people standing in a line indexed from 0 to `n - 1`. Each person **independently** chooses a direction:

-   `'L'`: **visible** only to people on their **right**
-   `'R'`: **visible** only to people on their **left**

A person at index `pos` sees others as follows:

-   A person `i < pos` is visible if and only if they choose `'L'`.
-   A person `i > pos` is visible if and only if they choose `'R'`.

Return the number of possible direction assignments such that the person at index `pos` sees **exactly** `k` people.

Since the answer may be large, return it **modulo** `10^9 + 7`.

**Constraints:**

-   `1 ≤ n ≤ 10^5`
-   `0 ≤ pos, k ≤ n - 1`
