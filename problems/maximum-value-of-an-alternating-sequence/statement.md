You are given three integers `n`, `s`, and `m`.

A sequence `seq` of integers of length `n` is considered **valid** if:

-   `seq[0] = s`.
-   The sequence is **alternating**, meaning that either:
    -   `seq[0] > seq[1] < seq[2] > ...`, or
    -   `seq[0] < seq[1] > seq[2] < ...`.
-   For every adjacent pair, `|seq[i] - seq[i - 1]| ≤ m`.

A sequence of length 1 is considered alternating.

Return the **maximum** possible element that can appear in any valid sequence.

**Constraints:**

-   `1 ≤ n, s ≤ 10^9`
-   `1 ≤ m ≤ 10^5`
