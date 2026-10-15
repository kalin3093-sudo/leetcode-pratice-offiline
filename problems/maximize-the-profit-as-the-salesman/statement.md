You are given an integer `n` representing the number of houses on a number line, numbered from `0` to `n - 1`.

Additionally, you are given a 2D integer array `offers` where `offers[i] = [starti, endi, goldi]`, indicating that `i^th` buyer wants to buy all the houses from `starti` to `endi` for `goldi` amount of gold.

As a salesman, your goal is to **maximize** your earnings by strategically selecting and selling houses to buyers.

Return *the maximum amount of gold you can earn*.

**Note** that different buyers can't buy the same house, and some houses may remain unsold.

**Constraints:**

-   `1 ≤ n ≤ 10^5`
-   `1 ≤ offers.length ≤ 10^5`
-   `offers[i].length == 3`
-   `0 ≤ starti ≤ endi ≤ n - 1`
-   `1 ≤ goldi ≤ 10^3`
