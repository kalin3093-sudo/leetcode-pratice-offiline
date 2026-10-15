You are given an integer array `nums`.

An integer `x` is called **special** if:

-   `x` appears **at least three** times in `nums`.
-   **All** occurrences of `x` are **equally spaced** in `nums`. In other words, if all occurrences of `x` are at indices `i1 < i2 < ... < im`, then `i2 - i1 = i3 - i2 = ... = im - im-1`.

Return the number of **distinct** special integers in `nums`.

**Constraints:**

-   `3 ≤ nums.length ≤ 10^5`
-   `1 ≤ nums[i] ≤ 10^9`
