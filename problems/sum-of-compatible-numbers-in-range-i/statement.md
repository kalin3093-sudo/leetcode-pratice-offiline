You are given two integers `n` and `k`.

A **positive** integer `x` is called **compatible** if it satisfies both of the following conditions:

-   `abs(n - x) ≤ k`
-   `(n & x) == 0`

Return the sum of all **compatible** integers `x`.

**Note**:

-   Here, `&` denotes the **bitwise AND** operator.
-   The **absolute** difference between integers `i` and `j` is defined as `abs(i - j)`.

**Constraints:**

-   `1 ≤ n ≤ 100`
-   `1 ≤ k ≤ 100`
