You are given a 2D integer array `stockPrices` where `stockPrices[i] = [dayi, pricei]` indicates the price of the stock on day `dayi` is `pricei`. A **line chart** is created from the array by plotting the points on an XY plane with the X-axis representing the day and the Y-axis representing the price and connecting adjacent points. One such example is shown below:

![](https://assets.leetcode.com/uploads/2022/03/30/1920px-pushkin_population_historysvg.png)

Return *the **minimum number of lines** needed to represent the line chart*.

**Constraints:**

-   `1 ≤ stockPrices.length ≤ 10^5`
-   `stockPrices[i].length == 2`
-   `1 ≤ dayi, pricei ≤ 10^9`
-   All `dayi` are **distinct**.
