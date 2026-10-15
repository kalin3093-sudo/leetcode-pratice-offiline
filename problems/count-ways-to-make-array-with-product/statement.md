You are given a 2D integer array, `queries`. For each `queries[i]`, where `queries[i] = [ni, ki]`, find the number of different ways you can place positive integers into an array of size `ni` such that the product of the integers is `ki`. As the number of ways may be too large, the answer to the `i^th` query is the number of ways **modulo** `10^9 + 7`.

Return *an integer array* `answer` *where* `answer.length == queries.length`*, and* `answer[i]` *is the answer to the* `i^th` *query.*

**Constraints:**

-   `1 ≤ queries.length ≤ 10^4`
-   `1 ≤ ni, ki ≤ 10^4`
