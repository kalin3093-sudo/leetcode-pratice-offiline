You are given two **0-indexed** integer arrays `nums1` and `nums2` of length `n`.

Let's define another **0-indexed** integer array, `nums3`, of length `n`. For each index `i` in the range `[0, n - 1]`, you can assign either `nums1[i]` or `nums2[i]` to `nums3[i]`.

Your task is to maximize the length of the **longest non-decreasing subarray** in `nums3` by choosing its values optimally.

Return *an integer representing the length of the **longest non-decreasing** subarray in* `nums3`.

**Note:** A **subarray** is a contiguous **non-empty** sequence of elements within an array.

**Constraints:**

-   `1 ≤ nums1.length == nums2.length == n ≤ 10^5`
-   `1 ≤ nums1[i], nums2[i] ≤ 10^9`
