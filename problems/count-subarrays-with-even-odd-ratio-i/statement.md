You are given an integer array `nums` and two integers `a` and `b`.

For a subarray, let:

-   `x` be the number of even elements.
-   `y` be the number of odd elements.

The ratio of even to odd elements in a subarray is defined as `x / y`, where ratios are compared by their exact rational values.

A subarray is considered **valid** if:

-   `y > 0`, and
-   `x / y ≤ a / b`.

Return the number of valid subarrays in `nums`.

**Constraints:**

-   `1 ≤ nums.length ≤ 1000`
-   `1 ≤ nums[i] ≤ 1000`
-   `1 ≤ a, b ≤ 1000`
