There are some prizes on the **X-axis**. You are given an integer array `prizePositions` that is **sorted in non-decreasing order**, where `prizePositions[i]` is the position of the `i^th` prize. There could be different prizes at the same position on the line. You are also given an integer `k`.

You are allowed to select two segments with integer endpoints. The length of each segment must be `k`. You will collect all prizes whose position falls within at least one of the two selected segments (including the endpoints of the segments). The two selected segments may intersect.

-   For example if `k = 2`, you can choose segments `[1, 3]` and `[2, 4]`, and you will win any prize i that satisfies `1 ≤ prizePositions[i] ≤ 3` or `2 ≤ prizePositions[i] ≤ 4`.

Return *the **maximum** number of prizes you can win if you choose the two segments optimally*.

**Constraints:**

-   `1 ≤ prizePositions.length ≤ 10^5`
-   `1 ≤ prizePositions[i] ≤ 10^9`
-   `0 ≤ k ≤ 10^9`
-   `prizePositions` is sorted in non-decreasing order.

.spoilerbutton {display:block; border:dashed; padding: 0px 0px; margin:10px 0px; font-size:150%; font-weight: bold; color:#000000; background-color:cyan; outline:0; } .spoiler {overflow:hidden;} .spoiler > div {-webkit-transition: all 0s ease;-moz-transition: margin 0s ease;-o-transition: all 0s ease;transition: margin 0s ease;} .spoilerbutton\[value="Show Message"\] + .spoiler > div {margin-top:-500%;} .spoilerbutton\[value="Hide Message"\] + .spoiler {padding:5px;}
