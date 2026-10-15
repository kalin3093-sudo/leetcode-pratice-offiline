You are given an integer array `nums` of length `n` and an array `queries`, where `queries[i] = [li, ri, thresholdi]`.

Return an array of integers `ans` where `ans[i]` is equal to the element in the subarray `nums[li...ri]` that appears **at least** `thresholdi` times, selecting the element with the **highest** frequency (choosing the **smallest** in case of a tie), or -1 if no such element *exists*.

**Constraints:**

-   `1 ≤ nums.length == n ≤ 10^4`
-   `1 ≤ nums[i] ≤ 10^9`
-   `1 ≤ queries.length ≤ 5 * 10^4`
-   `queries[i] = [li, ri, thresholdi]`
-   `0 ≤ li ≤ ri < n`
-   `1 ≤ thresholdi ≤ ri - li + 1`
