You are given two string arrays `positive_feedback` and `negative_feedback`, containing the words denoting positive and negative feedback, respectively. Note that **no** word is both positive and negative.

Initially every student has `0` points. Each positive word in a feedback report **increases** the points of a student by `3`, whereas each negative word **decreases** the points by `1`.

You are given `n` feedback reports, represented by a **0-indexed** string array `report` and a **0-indexed** integer array `student_id`, where `student_id[i]` represents the ID of the student who has received the feedback report `report[i]`. The ID of each student is **unique**.

Given an integer `k`, return *the top* `k` *students after ranking them in **non-increasing** order by their points*. In case more than one student has the same points, the one with the lower ID ranks higher.

**Constraints:**

-   `1 ≤ positive_feedback.length, negative_feedback.length ≤ 10^4`
-   `1 ≤ positive_feedback[i].length, negative_feedback[j].length ≤ 100`
-   Both `positive_feedback[i]` and `negative_feedback[j]` consists of lowercase English letters.
-   No word is present in both `positive_feedback` and `negative_feedback`.
-   `n == report.length == student_id.length`
-   `1 ≤ n ≤ 10^4`
-   `report[i]` consists of lowercase English letters and spaces `' '`.
-   There is a single space between consecutive words of `report[i]`.
-   `1 ≤ report[i].length ≤ 100`
-   `1 ≤ student_id[i] ≤ 10^9`
-   All the values of `student_id[i]` are **unique**.
-   `1 ≤ k ≤ n`
