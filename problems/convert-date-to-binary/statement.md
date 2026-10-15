You are given a string `date` representing a Gregorian calendar date in the `yyyy-mm-dd` format.

`date` can be written in its binary representation obtained by converting year, month, and day to their binary representations without any leading zeroes and writing them down in `year-month-day` format.

Return the **binary** representation of `date`.

**Constraints:**

-   `date.length == 10`
-   `date[4] == date[7] == '-'`, and all other `date[i]`'s are digits.
-   The input is generated such that `date` represents a valid Gregorian calendar date between Jan 1^st, 1900 and Dec 31^st, 2100 (both inclusive).
