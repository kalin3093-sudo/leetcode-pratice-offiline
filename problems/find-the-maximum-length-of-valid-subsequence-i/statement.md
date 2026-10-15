You are given an integer array `nums`.

A subsequence `sub` of `nums` with length `x` is called **valid** if it satisfies:

-   `(sub[0] + sub[1]) % 2 == (sub[1] + sub[2]) % 2 == ... == (sub[x - 2] + sub[x - 1]) % 2.`

Return the length of the **longest** **valid** subsequence of `nums`.

**Constraints:**

-   `2 ≤ nums.length ≤ 2 * 10^5`
-   `1 ≤ nums[i] ≤ 10^7`
