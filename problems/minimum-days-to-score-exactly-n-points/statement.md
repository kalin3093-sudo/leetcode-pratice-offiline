You are given an integer `n` representing a target score.

Your score starts at 0, and each day you either **earn** points or **skip**.

Points are earned during a streak. On the first day of a streak you earn 1 point, on the second day 2 points, on the third day 3 points, and so on. **Skipping** a day earns **nothing** and **resets** the streak, so the next time you earn points, you start from 1 again.

Return the **minimum** number of days, including any skipped days, needed to reach a score of **exactly** `n`.

**Constraints:**

-   `1 ≤ n ≤ 10^5`
