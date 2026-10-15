You are given two numeric strings `num1` and `num2` and two integers `max_sum` and `min_sum`. We denote an integer `x` to be *good* if:

-   `num1 ≤ x ≤ num2`
-   `min_sum ≤ digit_sum(x) ≤ max_sum`.

Return *the number of good integers*. Since the answer may be large, return it modulo `10^9 + 7`.

Note that `digit_sum(x)` denotes the sum of the digits of `x`.

**Constraints:**

-   `1 ≤ num1 ≤ num2 ≤ 10^22`
-   `1 ≤ min_sum ≤ max_sum ≤ 400`
