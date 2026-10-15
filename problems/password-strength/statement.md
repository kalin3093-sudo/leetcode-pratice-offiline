You are given a string `password`.

The **strength** of the password is calculated based on the following rules:

-   1 point for each distinct lowercase letter (`'a'` to `'z'`).
-   2 points for each distinct uppercase letter (`'A'` to `'Z'`).
-   3 points for each distinct digit (`'0'` to `'9'`).
-   5 points for each distinct special character from the set `"!@#$"`.

Each character contributes **at most** once, even if it appears multiple times.

Return an integer denoting the strength of the password.

**Constraints:**

-   `1 ≤ password.length ≤ 10^5`
-   `password` consists of lowercase and uppercase English letters, digits, and special characters from `"!@#$"`.
