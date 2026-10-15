You are given an integer array `nums`.

You start with an **empty** array `ans`. Repeat the following operation until `nums` is **empty**:

-   Identify **all** **distinct** values currently present in `nums`.
-   Remove **one** occurrence of every **distinct** value currently in `nums`, and append those values to `ans` in **ascending** order.

Return the array `ans`.

**Constraints:**

-   `1 ≤ nums.length ≤ 100`
-   `1 ≤ nums[i] ≤ 100`
