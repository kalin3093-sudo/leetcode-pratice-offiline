You are given two integer arrays `source` and `target`.

In one **operation**, you may choose two **distinct** indices `i` and `j` in `source`, along with any integer `delta`. Then update `source` as follows:

-   `source[i] = source[i] + source[j] - delta`
-   `source[j] = delta`

Return `true` if it is possible to make `source` equal to `target` after performing the operation **any** (including zero) number of times. Otherwise, return `false`.

**Constraints:**

-   `2 ≤ source.length == target.length ≤ 10^5`
-   `-10^9 ≤ source[i], target[i] ≤ 10^9`
