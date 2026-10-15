You are given the `root` of a **binary search tree** and an array `queries` of size `n` consisting of positive integers.

Find a **2D** array `answer` of size `n` where `answer[i] = [mini, maxi]`:

-   `mini` is the **largest** value in the tree that is smaller than or equal to `queries[i]`. If a such value does not exist, add `-1` instead.
-   `maxi` is the **smallest** value in the tree that is greater than or equal to `queries[i]`. If a such value does not exist, add `-1` instead.

Return *the array* `answer`.

**Constraints:**

-   The number of nodes in the tree is in the range `[2, 10^5]`.
-   `1 ≤ Node.val ≤ 10^6`
-   `n == queries.length`
-   `1 ≤ n ≤ 10^5`
-   `1 ≤ queries[i] ≤ 10^6`
