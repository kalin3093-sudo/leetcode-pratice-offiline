You are given a string `moves` consisting of the characters `'U'`, `'D'`, `'L'`, `'R'`, and `'_'`.

Starting from the origin `(0, 0)`, each character represents one move on a 2D plane:

-   `'U'`: Move up by 1 unit.
-   `'D'`: Move down by 1 unit.
-   `'L'`: Move left by 1 unit.
-   `'R'`: Move right by 1 unit.
-   `'_'`: Can be independently replaced with any one of `'U'`, `'D'`, `'L'`, or `'R'`.

Return the maximum **Manhattan distance** from the origin that can be achieved after all moves have been performed.

**Constraints:**

-   `1 ≤ moves.length ≤ 10^5`
-   `moves` consists of only `'U'`, `'D'`, `'L'`, `'R'`, and `'_'`.
