You are given a 2D integer array `drones`, where `drones[i] = [xi, yi, rangei]` represents the x-coordinate, y-coordinate, and travel range of the `i^th` drone.

You are also given an integer array `target = [tx, ty]`, representing the coordinates of the target.

A drone `drones[i]` can reach the target if the Manhattan distance between its coordinates and the target coordinates is **less than or equal** to its `rangei`.

Return the **index** of the reachable drone with the **minimum Manhattan distance** to the target. If there is a tie, return the **smallest index**. If no drone can reach the target, return `-1`.

**Constraints:**

-   `1 ≤ drones.length ≤ 100`
-   `drones[i] = [xi, yi, rangei]`
-   `target = [tx, ty]`
-   `-25 ≤ xi, yi, tx, ty ≤ 25`
-   `1 ≤ rangei ≤ 100`
