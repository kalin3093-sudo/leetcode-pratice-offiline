You are given two integer arrays `prices` and `discounts`.

The value `prices[i]` represents the price of the `i^th` item, and `discounts[j]` represents a discount percentage.

You may apply discounts subject to the following rules:

-   Each discount can be applied to **at most** one item.
-   Each item can receive **at most** one discount.
-   An item may also receive no discount.

If a discount of `d` percent is applied to an item with price `p`, its final price becomes `(p * (100 - d)) / 100`. The final price is **not** rounded.

Return the **minimum** possible sum of final prices after assigning discounts optimally. Answers within `10^-5` of the actual answer will be accepted.

**Constraints:**

-   `1 ≤ prices.length, discounts.length ≤ 10^5`
-   `1 ≤ prices[i] ≤ 10^5`
-   `1 ≤ discounts[j] ≤ 100`
