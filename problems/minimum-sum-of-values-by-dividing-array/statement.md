You are given two arrays `nums` and `andValues` of length `n` and `m` respectively.

The **value** of an array is equal to the **last** element of that array.

You have to divide `nums` into `m` **disjoint contiguous** subarrays such that for the `i^th` subarray `[li, ri]`, the bitwise `AND` of the subarray elements is equal to `andValues[i]`, in other words, `nums[li] & nums[li + 1] & ... & nums[ri] == andValues[i]` for all `1 ≤ i ≤ m`, where `&` represents the bitwise `AND` operator.

Return *the **minimum** possible sum of the **values** of the* `m` *subarrays* `nums` *is divided into*. *If it is not possible to divide* `nums` *into* `m` *subarrays satisfying these conditions, return* `-1`.

**Constraints:**

-   `1 ≤ n == nums.length ≤ 10^4`
-   `1 ≤ m == andValues.length ≤ min(n, 10)`
-   `1 ≤ nums[i] < 10^5`
-   `0 ≤ andValues[j] < 10^5`
