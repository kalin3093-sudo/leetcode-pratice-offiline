You are given two **0-indexed** arrays `nums` and `cost` consisting each of `n` **positive** integers.

You can do the following operation **any** number of times:

-   Increase or decrease **any** element of the array `nums` by `1`.

The cost of doing one operation on the `i^th` element is `cost[i]`.

Return *the **minimum** total cost such that all the elements of the array* `nums` *become **equal***.

**Constraints:**

-   `n == nums.length == cost.length`
-   `1 ≤ n ≤ 10^5`
-   `1 ≤ nums[i], cost[i] ≤ 10^6`
-   Test cases are generated in a way that the output doesn't exceed 2^53\-1
