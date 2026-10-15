You are given two integer arrays `arr` and `brr` of length `n`, and an integer `k`. You can perform the following operations on `arr` *any* number of times:

-   Split `arr` into *any* number of **contiguous** subarrays and rearrange these subarrays in *any order*. This operation has a fixed cost of `k`.
-   Choose any element in `arr` and add or subtract a positive integer `x` to it. The cost of this operation is `x`.
    

Return the **minimum** total cost to make `arr` **equal** to `brr`.

**Constraints:**

-   `1 ≤ arr.length == brr.length ≤ 10^5`
-   `0 ≤ k ≤ 2 * 10^10`
-   `-10^5 ≤ arr[i] ≤ 10^5`
-   `-10^5 ≤ brr[i] ≤ 10^5`
