You are given an integer `n` representing an array `colors` of length `n` where all elements are set to 0's meaning **uncolored**. You are also given a 2D integer array `queries` where `queries[i] = [indexi, colori]`. For the `i^th` **query**:

-   Set `colors[indexi]` to `colori`.
-   Count the number of adjacent pairs in `colors` which have the same color (regardless of `colori`).

Return an array `answer` of the same length as `queries` where `answer[i]` is the answer to the `i^th` query.

**Constraints:**

-   `1 ≤ n ≤ 10^5`
-   `1 ≤ queries.length ≤ 10^5`
-   `queries[i].length == 2`
-   `0 ≤ indexi ≤ n - 1`
-   `1 ≤  colori ≤ 10^5`
