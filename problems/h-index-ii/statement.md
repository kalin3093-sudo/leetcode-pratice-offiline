Given an array of integers `citations` where `citations[i]` is the number of citations a researcher received for their `i^th` paper and `citations` is sorted in **non-descending order**, return *the researcher's h-index*.

According to the [definition of h-index on Wikipedia](https://en.wikipedia.org/wiki/H-index): The h-index is defined as the maximum value of `h` such that the given researcher has published at least `h` papers that have each been cited at least `h` times.

You must write an algorithm that runs in logarithmic time.

**Constraints:**

-   `n == citations.length`
-   `1 ≤ n ≤ 10^5`
-   `0 ≤ citations[i] ≤ 1000`
-   `citations` is sorted in **ascending order**.
