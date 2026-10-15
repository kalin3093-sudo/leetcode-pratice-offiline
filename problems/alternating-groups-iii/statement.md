There are some red and blue tiles arranged circularly. You are given an array of integers `colors` and a 2D integers array `queries`.

The color of tile `i` is represented by `colors[i]`:

-   `colors[i] == 0` means that tile `i` is **red**.
-   `colors[i] == 1` means that tile `i` is **blue**.

An **alternating** group is a contiguous subset of tiles in the circle with **alternating** colors (each tile in the group except the first and last one has a different color from its **adjacent** tiles in the group).

You have to process queries of two types:

-   `queries[i] = [1, sizei]`, determine the count of **alternating** groups with size `sizei`.
-   `queries[i] = [2, indexi, colori]`, change `colors[indexi]` to `colori`.

Return an array `answer` containing the results of the queries of the first type *in order*.

**Note** that since `colors` represents a **circle**, the **first** and the **last** tiles are considered to be next to each other.

**Constraints:**

-   `4 ≤ colors.length ≤ 5 * 10^4`
-   `0 ≤ colors[i] ≤ 1`
-   `1 ≤ queries.length ≤ 5 * 10^4`
-   `queries[i][0] == 1` or `queries[i][0] == 2`
-   For all `i` that:
    -   `queries[i][0] == 1`: `queries[i].length == 2`, `3 ≤ queries[i][1] ≤ colors.length - 1`
    -   `queries[i][0] == 2`: `queries[i].length == 3`, `0 ≤ queries[i][1] ≤ colors.length - 1`, `0 ≤ queries[i][2] ≤ 1`
