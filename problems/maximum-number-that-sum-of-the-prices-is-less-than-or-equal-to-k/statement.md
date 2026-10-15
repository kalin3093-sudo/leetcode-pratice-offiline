You are given an integer `k` and an integer `x`. The price of a number `num` is calculated by the count of set bits at positions `x`, `2x`, `3x`, etc., in its binary representation, starting from the least significant bit. The following table contains examples of how price is calculated.

| x | num | Binary Representation | Price |
| --- | --- | --- | --- |
| 1 | 13 | 00000**1****1**0**1** | 3 |
| 2 | 13 | 00000**1**101 | 1 |
| 2 | 233 | 0**1**1**1**0**1**001 | 3 |
| 3 | 13 | 000001**1**01 | 1 |
| 3 | 362 | **1**01**1**01010 | 2 |

The **accumulated price** of `num` is the **total** price of numbers from `1` to `num`. `num` is considered **cheap** if its accumulated price is less than or equal to `k`.

Return the **greatest** cheap number.

**Constraints:**

-   `1 ≤ k ≤ 10^15`
-   `1 ≤ x ≤ 8`
