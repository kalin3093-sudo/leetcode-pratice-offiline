You are given an integer `n` and a 2D array `requirements`, where `requirements[i] = [endi, cnti]` represents the end index and the **inversion** count of each requirement.

A pair of indices `(i, j)` from an integer array `nums` is called an **inversion** if:

-   `i < j` and `nums[i] > nums[j]`

Return the number of permutations `perm` of `[0, 1, 2, ..., n - 1]` such that for **all** `requirements[i]`, `perm[0..endi]` has exactly `cnti` inversions.

Since the answer may be very large, return it **modulo** `10^9 + 7`.

**Constraints:**

-   `2 ≤ n ≤ 300`
-   `1 ≤ requirements.length ≤ n`
-   `requirements[i] = [endi, cnti]`
-   `0 ≤ endi ≤ n - 1`
-   `0 ≤ cnti ≤ 400`
-   The input is generated such that there is at least one `i` such that `endi == n - 1`.
-   The input is generated such that all `endi` are unique.
