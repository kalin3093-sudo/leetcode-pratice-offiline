You are given an array of integers `nums`. Some values in `nums` are **missing** and are denoted by -1.

You must choose a pair of **positive** integers `(x, y)` **exactly once** and replace each **missing** element with *either* `x` or `y`.

You need to **minimize** the **maximum** **absolute difference** between *adjacent* elements of `nums` after replacements.

Return the **minimum** possible difference.

**Constraints:**

-   `2 ≤ nums.length ≤ 10^5`
-   `nums[i]` is either -1 or in the range `[1, 10^9]`.
