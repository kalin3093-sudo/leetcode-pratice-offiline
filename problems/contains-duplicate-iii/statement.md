You are given an integer array `nums` and two integers `indexDiff` and `valueDiff`.

Find a pair of indices `(i, j)` such that:

-   `i != j`,
-   `abs(i - j) ≤ indexDiff`.
-   `abs(nums[i] - nums[j]) ≤ valueDiff`, and

Return `true` *if such pair exists or* `false` *otherwise*.

**Constraints:**

-   `2 ≤ nums.length ≤ 10^5`
-   `-10^9 ≤ nums[i] ≤ 10^9`
-   `1 ≤ indexDiff ≤ nums.length`
-   `0 ≤ valueDiff ≤ 10^9`
