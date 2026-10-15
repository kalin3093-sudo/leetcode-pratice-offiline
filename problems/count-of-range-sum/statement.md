Given an integer array `nums` and two integers `lower` and `upper`, return *the number of range sums that lie in* `[lower, upper]` *inclusive*.

Range sum `S(i, j)` is defined as the sum of the elements in `nums` between indices `i` and `j` inclusive, where `i ≤ j`.

**Constraints:**

-   `1 ≤ nums.length ≤ 10^5`
-   `-2^31 ≤ nums[i] ≤ 2^31 - 1`
-   `-10^5 ≤ lower ≤ upper ≤ 10^5`
-   The answer is **guaranteed** to fit in a **32-bit** integer.
