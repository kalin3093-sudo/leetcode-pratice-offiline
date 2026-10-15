You have two fruit baskets containing `n` fruits each. You are given two **0-indexed** integer arrays `basket1` and `basket2` representing the cost of fruit in each basket. You want to make both baskets **equal**. To do so, you can use the following operation as many times as you want:

-   Choose two indices `i` and `j`, and swap the `i^th` fruit of `basket1` with the `j^th` fruit of `basket2`.
-   The cost of the swap is `min(basket1[i], basket2[j])`.

Two baskets are considered equal if sorting them according to the fruit cost makes them exactly the same baskets.

Return *the minimum cost to make both the baskets equal or* `-1` *if impossible.*

**Constraints:**

-   `basket1.length == basket2.length`
-   `1 ≤ basket1.length ≤ 10^5`
-   `1 ≤ basket1[i], basket2[i] ≤ 10^9`
