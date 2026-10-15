import java.util.*;
class Marker {
    public String alphabetBoardPath(String target) {
        StringBuilder sb = new StringBuilder();
        int r = 0, c = 0;
        for (char ch : target.toCharArray()) {
            int idx = ch - 'a';
            int tr = idx / 5, tc = idx % 5;
            if (r == 5) {
                while (r > tr) { sb.append('U'); r--; }
                while (c < tc) { sb.append('R'); c++; }
                while (c > tc) { sb.append('L'); c--; }
            } else if (tr == 5) {
                while (c < tc) { sb.append('R'); c++; }
                while (c > tc) { sb.append('L'); c--; }
                while (r < tr) { sb.append('D'); r++; }
            } else {
                while (r > tr) { sb.append('U'); r--; }
                while (r < tr) { sb.append('D'); r++; }
                while (c < tc) { sb.append('R'); c++; }
                while (c > tc) { sb.append('L'); c--; }
            }
            sb.append('!');
        }
        return sb.toString();
    }

    public boolean isCorrect(String target, String output) {
        if (output == null || target == null) return false;
        int r = 0, c = 0;
        StringBuilder got = new StringBuilder();
        for (char op : output.toCharArray()) {
            if (op == '!') {
                got.append((char) ('a' + r * 5 + c));
            } else if (op == 'U') {
                if (r == 0) return false;
                r--;
            } else if (op == 'D') {
                if (r == 5 || (r == 4 && c > 0)) return false;
                r++;
            } else if (op == 'L') {
                if (c == 0 || r == 5) return false;
                c--;
            } else if (op == 'R') {
                if (c == 4 || r == 5) return false;
                c++;
            } else {
                return false;
            }
        }
        return got.toString().equals(target);
    }
}
