You are given an integer array `digitSum` of length `n`.

An array `arr` of length `n` is considered **valid** if:

-   `0 ≤ arr[i] ≤ 5000`
-   it is **non-decreasing**.
-   the **sum of the digits** of `arr[i]` **equals** `digitSum[i]`.

Return an integer denoting the number of **distinct valid arrays**. Since the answer may be large, return it modulo `10^9 + 7`.

An array is said to be **non-decreasing** if each element is greater than or equal to the previous element, if it exists.

**Constraints:**

-   `1 ≤ digitSum.length ≤ 1000`
-   `0 ≤ digitSum[i] ≤ 50`
