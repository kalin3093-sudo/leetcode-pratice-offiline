There are `k` workers who want to move `n` boxes from the right (old) warehouse to the left (new) warehouse. You are given the two integers `n` and `k`, and a 2D integer array `time` of size `k x 4` where `time[i] = [righti, picki, lefti, puti]`.

The warehouses are separated by a river and connected by a bridge. Initially, all `k` workers are waiting on the left side of the bridge. To move the boxes, the `i^th` worker can do the following:

-   Cross the bridge to the right side in `righti` minutes.
-   Pick a box from the right warehouse in `picki` minutes.
-   Cross the bridge to the left side in `lefti` minutes.
-   Put the box into the left warehouse in `puti` minutes.

The `i^th` worker is **less efficient** than the j`^th` worker if either condition is met:

-   `lefti + righti > leftj + rightj`
-   `lefti + righti == leftj + rightj` and `i > j`

The following rules regulate the movement of the workers through the bridge:

-   Only one worker can use the bridge at a time.
-   When the bridge is unused prioritize the **least efficient** worker (who have picked up the box) on the right side to cross. If not, prioritize the **least efficient** worker on the left side to cross.
-   If enough workers have already been dispatched from the left side to pick up all the remaining boxes, **no more** workers will be sent from the left side.

Return the **elapsed minutes** at which the last box reaches the **left side of the bridge**.

**Constraints:**

-   `1 ≤ n, k ≤ 10^4`
-   `time.length == k`
-   `time[i].length == 4`
-   `1 ≤ lefti, picki, righti, puti ≤ 1000`
