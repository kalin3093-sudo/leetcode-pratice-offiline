There are two mice and `n` different types of cheese, each type of cheese should be eaten by exactly one mouse.

A point of the cheese with index `i` (**0-indexed**) is:

-   `reward1[i]` if the first mouse eats it.
-   `reward2[i]` if the second mouse eats it.

You are given a positive integer array `reward1`, a positive integer array `reward2`, and a non-negative integer `k`.

Return ***the maximum** points the mice can achieve if the first mouse eats exactly* `k` *types of cheese.*

**Constraints:**

-   `1 ≤ n == reward1.length == reward2.length ≤ 10^5`
-   `1 ≤ reward1[i], reward2[i] ≤ 1000`
-   `0 ≤ k ≤ n`
