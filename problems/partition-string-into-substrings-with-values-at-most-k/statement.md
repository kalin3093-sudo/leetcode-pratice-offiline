You are given a string `s` consisting of digits from `1` to `9` and an integer `k`.

A partition of a string `s` is called **good** if:

-   Each digit of `s` is part of **exactly** one substring.
-   The value of each substring is less than or equal to `k`.

Return *the **minimum** number of substrings in a **good** partition of* `s`. If no **good** partition of `s` exists, return `-1`.

**Note** that:

-   The **value** of a string is its result when interpreted as an integer. For example, the value of `"123"` is `123` and the value of `"1"` is `1`.
-   A **substring** is a contiguous sequence of characters within a string.

**Constraints:**

-   `1 ≤ s.length ≤ 10^5`
-   `s[i]` is a digit from `'1'` to `'9'`.
-   `1 ≤ k ≤ 10^9`

.spoilerbutton {display:block; border:dashed; padding: 0px 0px; margin:10px 0px; font-size:150%; font-weight: bold; color:#000000; background-color:cyan; outline:0; } .spoiler {overflow:hidden;} .spoiler > div {-webkit-transition: all 0s ease;-moz-transition: margin 0s ease;-o-transition: all 0s ease;transition: margin 0s ease;} .spoilerbutton\[value="Show Message"\] + .spoiler > div {margin-top:-500%;} .spoilerbutton\[value="Hide Message"\] + .spoiler {padding:5px;}
