You are given an array `nums` of `n` integers, and a 2D integer array `queries` of size `q`, where `queries[i] = [li, ri]`.

For each query, you must find the **maximum XOR score** of any subarray of `nums[li..ri]`.

The **XOR score** of an array `a` is found by repeatedly applying the following operations on `a` so that only one element remains, that is the **score**:

-   Simultaneously replace `a[i]` with `a[i] XOR a[i + 1]` for all indices `i` except the last one.
-   Remove the last element of `a`.

Return an array `answer` of size `q` where `answer[i]` is the answer to query `i`.

**Constraints:**

-   `1 ≤ n == nums.length ≤ 2000`
-   `0 ≤ nums[i] ≤ 2^31 - 1`
-   `1 ≤ q == queries.length ≤ 10^5`
-   `queries[i].length == 2`
-   `queries[i] = [li, ri]`
-   `0 ≤ li ≤ ri ≤ n - 1`
