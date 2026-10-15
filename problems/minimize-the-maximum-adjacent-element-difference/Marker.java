import java.util.*;
class Marker {
    public int minDifference(int[] nums) {
        int maxPositiveGap = 0;
        int mn = 1_000_000_000;
        int mx = 0;

        for (int i = 1; i < nums.length; ++i) {
            if ((nums[i - 1] == -1) != (nums[i] == -1)) {
                final int positive = Math.max(nums[i - 1], nums[i]);
                mn = Math.min(mn, positive);
                mx = Math.max(mx, positive);
            } else {
                maxPositiveGap = Math.max(maxPositiveGap, Math.abs(nums[i - 1] - nums[i]));
            }
        }

        int l = maxPositiveGap;
        int r = (mx - mn + 1) / 2;

        while (l < r) {
            final int m = (l + r) / 2;
            if (check(nums, m, mn + m, mx - m)) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    private boolean check(int[] nums, int m, int x, int y) {
        int gapLength = 0;
        int prev = 0;

        for (final int num : nums) {
            if (num == -1) {
                ++gapLength;
                continue;
            }
            if (prev > 0 && gapLength > 0) {
                if (gapLength == 1 && !checkSingleGap(prev, num, m, x, y)) return false;
                if (gapLength > 1 && !checkMultipleGaps(prev, num, m, x, y)) return false;
            }
            prev = num;
            gapLength = 0;
        }

        if (nums[0] == -1) {
            final int num = findFirstNumber(nums, 0, 1);
            if (num != -1 && !checkBoundaryGaps(num, m, x, y)) return false;
        }
        if (nums[nums.length - 1] == -1) {
            final int num = findFirstNumber(nums, nums.length - 1, -1);
            if (num != -1 && !checkBoundaryGaps(num, m, x, y)) return false;
        }
        return true;
    }

    // [a, -1, b] filled with x or y
    private boolean checkSingleGap(int a, int b, int m, int x, int y) {
        final int gapWithX = Math.max(Math.abs(a - x), Math.abs(b - x));
        final int gapWithY = Math.max(Math.abs(a - y), Math.abs(b - y));
        return Math.min(gapWithX, gapWithY) <= m;
    }

    // [a, -1, -1, ..., -1, b] filled with x and y
    private boolean checkMultipleGaps(int a, int b, int m, int x, int y) {
        final int ax = Math.abs(a - x);
        final int ay = Math.abs(a - y);
        final int bx = Math.abs(b - x);
        final int by = Math.abs(b - y);
        final int xy = Math.abs(x - y);
        final int gapAllX = Math.max(ax, bx);
        final int gapAllY = Math.max(ay, by);
        final int gapXToY = Math.max(Math.max(ax, xy), by);
        final int gapYToX = Math.max(Math.max(ay, xy), bx);
        return Math.min(Math.min(gapAllX, gapAllY), Math.min(gapXToY, gapYToX)) <= m;
    }

    // [a, -1, -1, ...] or [..., -1, -1, a]
    private boolean checkBoundaryGaps(int a, int m, int x, int y) {
        return Math.min(Math.abs(a - x), Math.abs(a - y)) <= m;
    }

    private int findFirstNumber(int[] nums, int start, int step) {
        int i = start;
        while (i >= 0 && i < nums.length && nums[i] == -1) i += step;
        return i >= 0 && i < nums.length ? nums[i] : -1;
    }

    public boolean isCorrect(int[] nums, int output) {
        return minDifference(nums) == output;
    }
}
