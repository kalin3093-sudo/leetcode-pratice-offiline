You are given an array of integers `nums` with length `n`, and a positive **odd** integer `k`.

Select exactly **`k`** disjoint subarrays **`sub1, sub2, ..., subk`** from `nums` such that the last element of `subi` appears before the first element of `sub{i+1}` for all `1 ≤ i ≤ k-1`. The goal is to maximize their combined strength.

The strength of the selected subarrays is defined as:

`strength = k * sum(sub1)- (k - 1) * sum(sub2) + (k - 2) * sum(sub3) - ... - 2 * sum(sub{k-1}) + sum(subk)`

where **`sum(subi)`** is the sum of the elements in the `i`\-th subarray.

Return the **maximum** possible strength that can be obtained from selecting exactly **`k`** disjoint subarrays from `nums`.

**Note** that the chosen subarrays **don't** need to cover the entire array.

**Constraints:**

-   `1 ≤ n ≤ 10^4`
-   `-10^9 ≤ nums[i] ≤ 10^9`
-   `1 ≤ k ≤ n`
-   `1 ≤ n * k ≤ 10^6`
-   `k` is odd.
