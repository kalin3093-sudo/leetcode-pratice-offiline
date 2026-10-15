You are given an integer array `a` of size 4 and another integer array `b` of size **at least** 4.

You need to choose 4 indices `i0`, `i1`, `i2`, and `i3` from the array `b` such that `i0 < i1 < i2 < i3`. Your score will be equal to the value `a[0] * b[i0] + a[1] * b[i1] + a[2] * b[i2] + a[3] * b[i3]`.

Return the **maximum** score you can achieve.

**Constraints:**

-   `a.length == 4`
-   `4 ≤ b.length ≤ 10^5`
-   `-10^5 ≤ a[i], b[i] ≤ 10^5`
