You are given two integer arrays `nums` and `target`, each of length `n`, where `nums[i]` is the current value at index `i` and `target[i]` is the desired value at index `i`.

You may perform the following operation any number of times (including zero):

-   Choose an integer value `x`
-   Find all **maximal contiguous segments** where `nums[i] == x` (a segment is **maximal** if it cannot be extended to the left or right while keeping all values equal to `x`)
-   For each such segment `[l, r]`, update **simultaneously**:
    -   `nums[l] = target[l], nums[l + 1] = target[l + 1], ..., nums[r] = target[r]`

Return the **minimum** number of operations required to make `nums` equal to `target`.

**Constraints:**

-   `1 ≤ n == nums.length == target.length ≤ 10^5`
-   `1 ≤ nums[i], target[i] ≤ 10^5`
