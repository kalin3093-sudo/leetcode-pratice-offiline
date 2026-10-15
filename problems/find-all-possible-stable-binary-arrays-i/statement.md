You are given 3 positive integers `num_zeros`, `num_ones`, and `limit`.

A binary array `arr` is called **stable** if:

-   The number of occurrences of 0 in `arr` is **exactly** `num_zeros`.
-   The number of occurrences of 1 in `arr` is **exactly** `num_ones`.
-   Each subarray of `arr` with a size greater than `limit` must contain **at least** one occurrence of **both** 0 and 1.

Return an integer denoting the *total* number of **stable** binary arrays.

Since the answer may be very large, return it **modulo** `10^9 + 7`.

**Constraints:**

-   `1 ≤ zero, one, limit ≤ 200`
