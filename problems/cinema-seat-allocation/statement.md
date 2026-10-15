![](https://assets.leetcode.com/uploads/2020/02/14/cinema_seats_1.png)

A cinema has `n` rows of seats, numbered from 1 to `n`. Each row has 10 seats, numbered from 1 to 10.

You are given a 2D integer array `reservedSeats`, where `reservedSeats[i] = [rowi, seati]` means that seat `seati` in row `rowi` is already reserved.

A four-person group must be assigned to four seats in the **same** row. The group can be seated in one of the following seat blocks:

-   seats `2, 3, 4, 5`
-   seats `4, 5, 6, 7`
-   seats `6, 7, 8, 9`

A block can be used only if **none** of its seats are reserved. Each seat can be assigned to **at most** one group.

Return an integer denoting the **maximum** number of four-person groups that can be assigned.

**Constraints:**

-   `1 ≤ n ≤ 10^9`
-   `1 ≤ reservedSeats.length ≤ min(10 * n, 10^4)`
-   `reservedSeats[i] == [rowi, seati]`
-   `1 ≤ rowi ≤ n`
-   `1 ≤ seati ≤ 10`
-   All `reservedSeats[i]` are distinct.
