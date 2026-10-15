import java.util.*;

class Marker {
    static double unionWidth(int[][] squares, double lo, double hi) {
        List<int[]> iv = new ArrayList<>();
        for (int[] sq : squares) {
            long y0 = sq[1];
            long y1 = y0 + sq[2];
            if (y0 <= (long) lo && y1 >= (long) hi)
                iv.add(new int[] { sq[0], (int) (sq[0] + (long) sq[2]) });
        }
        iv.sort((a, b) -> Integer.compare(a[0], b[0]));
        long total = 0;
        int start = -1, end = -1;
        for (int[] p : iv) {
            if (start < 0) {
                start = p[0];
                end = p[1];
            } else if (p[0] > end) {
                total += end - start;
                start = p[0];
                end = p[1];
            } else {
                end = Math.max(end, p[1]);
            }
        }
        if (start >= 0)
            total += end - start;
        return total;
    }

    public double separateSquares(int[][] squares) {
        int n = squares.length;
        long[] all = new long[2 * n];
        for (int i = 0; i < n; i++) {
            all[2 * i] = squares[i][1];
            all[2 * i + 1] = (long) squares[i][1] + squares[i][2];
        }
        Arrays.sort(all);
        int m = 0;
        long[] cy = new long[2 * n];
        for (long v : all)
            if (m == 0 || cy[m - 1] != v)
                cy[m++] = v;

        double[] below = new double[m];
        double[] widths = new double[m];
        double total = 0;
        for (int j = 1; j < m; j++) {
            widths[j] = unionWidth(squares, cy[j - 1], cy[j]);
            total += widths[j] * (cy[j] - cy[j - 1]);
            below[j] = total;
        }
        double half = total / 2.0;
        for (int j = 1; j < m; j++) {
            if (below[j] >= half) {
                double prev = below[j - 1];
                if (widths[j] <= 0)
                    return cy[j - 1];
                return cy[j - 1] + (half - prev) / widths[j];
            }
        }
        return cy[m - 1];
    }

    public boolean isCorrect(int[][] squares, double output) {
        double expected = separateSquares(squares);
        return Math.abs(expected - output) <= 1e-5;
    }
}
