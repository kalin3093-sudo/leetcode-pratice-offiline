You are given a **0-indexed** integer array `nums`.

Initially, all of the indices are unmarked. You are allowed to make this operation any number of times:

-   Pick two **different unmarked** indices `i` and `j` such that `2 * nums[i] ≤ nums[j]`, then mark `i` and `j`.

Return *the maximum possible number of marked indices in `nums` using the above operation any number of times*.

**Constraints:**

-   `1 ≤ nums.length ≤ 10^5`
-   `1 ≤ nums[i] ≤ 10^9`

.spoilerbutton {display:block; border:dashed; padding: 0px 0px; margin:10px 0px; font-size:150%; font-weight: bold; color:#000000; background-color:cyan; outline:0; } .spoiler {overflow:hidden;} .spoiler > div {-webkit-transition: all 0s ease;-moz-transition: margin 0s ease;-o-transition: all 0s ease;transition: margin 0s ease;} .spoilerbutton\[value="Show Message"\] + .spoiler > div {margin-top:-500%;} .spoilerbutton\[value="Hide Message"\] + .spoiler {padding:5px;}
