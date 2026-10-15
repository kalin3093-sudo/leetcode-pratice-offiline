You are given two integers `n` and `k` and two integer arrays `speed` and `efficiency` both of length `n`. There are `n` engineers numbered from `1` to `n`. `speed[i]` and `efficiency[i]` represent the speed and efficiency of the `i^th` engineer respectively.

Choose **at most** `k` different engineers out of the `n` engineers to form a team with the maximum **performance**.

The performance of a team is the sum of its engineers' speeds multiplied by the minimum efficiency among its engineers.

Return *the maximum performance of this team*. Since the answer can be a huge number, return it **modulo** `10^9 + 7`.

**Constraints:**

-   `1 ≤ k ≤ n ≤ 10^5`
-   `speed.length == n`
-   `efficiency.length == n`
-   `1 ≤ speed[i] ≤ 10^5`
-   `1 ≤ efficiency[i] ≤ 10^8`
