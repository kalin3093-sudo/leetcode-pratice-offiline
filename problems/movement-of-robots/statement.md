Some robots are standing on an infinite number line with their initial coordinates given by a **0-indexed** integer array `nums` and will start moving once given the command to move. The robots will move a unit distance each second.

You are given a string `s` denoting the direction in which robots will move on command. `'L'` means the robot will move towards the left side or negative side of the number line, whereas `'R'` means the robot will move towards the right side or positive side of the number line.

If two robots collide, they will start moving in opposite directions.

Return *the sum of distances between all the pairs of robots* `d` *seconds after the command.* Since the sum can be very large, return it modulo `10^9 + 7`.

**Note:**

-   For two robots at the index `i` and `j`, pair `(i,j)` and pair `(j,i)` are considered the same pair.
-   When robots collide, they **instantly change** their directions without wasting any time.
-   Collision happens when two robots share the same place in a moment.
    -   For example, if a robot is positioned in 0 going to the right and another is positioned in 2 going to the left, the next second they'll be both in 1 and they will change direction and the next second the first one will be in 0, heading left, and another will be in 2, heading right.
    -   For example, if a robot is positioned in 0 going to the right and another is positioned in 1 going to the left, the next second the first one will be in 0, heading left, and another will be in 1, heading right.

**Constraints:**

-   `2 ≤ nums.length ≤ 10^5`
-   `-2 * 10^9 ≤ nums[i] ≤ 2 * 10^9`
-   `0 ≤ d ≤ 10^9`
-   `nums.length == s.length` 
-   `s` consists of 'L' and 'R' only
-   `nums[i]` will be unique.
