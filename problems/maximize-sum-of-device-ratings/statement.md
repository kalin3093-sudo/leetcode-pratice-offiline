You are given a 2D integer array `units` of size `m × n` where `units[i][j]` represents the capacity of the `j^th` unit in the `i^th` device. Each device contains **exactly** `n` units.

The **rating** of a device is the **minimum** capacity among all its units.

You may perform the following operation any number of times (including zero):

-   Choose a device `i` that has **not been** used as a source before.
-   Remove **exactly** one unit from device `i` and add it to **any** different device.
-   Then mark device `i` as used, so it cannot be chosen again as a source.

Return the **maximum** possible sum of the ratings of all devices after any number of such operations.

**Note:**

-   Devices can receive units from multiple devices, regardless of whether they have been selected.
-   The rating of an empty device is 0.

**Constraints:**

-   `1 ≤ m == units.length ≤ 10^5`
-   `1 ≤ n == units[i].length ≤ 10^5`
-   `m * n ≤ 2 * 10^5`
-   `1 ≤ units[i][j] ≤ 10^5`
