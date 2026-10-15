Given a string `s`, you have two types of operation:

1.  Choose an index `i` in the string, and let `c` be the character in position `i`. **Delete** the **closest occurrence** of `c` to the **left** of `i` (if exists).
2.  Choose an index `i` in the string, and let `c` be the character in position `i`. **Delete** the **closest occurrence** of `c` to the **right** of `i` (if exists).

Your task is to **minimize** the length of `s` by performing the above operations zero or more times.

Return an integer denoting the length of the **minimized** string.

**Constraints:**

-   `1 ≤ s.length ≤ 100`
-   `s` contains only lowercase English letters
