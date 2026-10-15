You are given the `head` of a linked list containing unique integer values and an integer array `nums` that is a subset of the linked list values.

Return the number of **connected** **components** in `nums`. A connected component is a non-empty, maximal sequence of **consecutive** nodes in the linked list such that every node's value belongs to nums.

**Constraints:**

-   The number of nodes in the linked list is `n`.
-   `1 ≤ n ≤ 10^4`
-   `0 ≤ Node.val < n`
-   All the values `Node.val` are **unique**.
-   `1 ≤ nums.length ≤ n`
-   `0 ≤ nums[i] < n`
-   All the values of `nums` are **unique**.
