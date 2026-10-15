import java.util.*;

class Marker {
    public int totalWaviness(int num1, int num2) {
        int ans = 0;
        for (int x = num1; x <= num2; x++) ans += waviness(x);
        return ans;
    }

    private int waviness(int x) {
        char[] d = Integer.toString(x).toCharArray();
        int w = 0;
        for (int i = 1; i < d.length - 1; i++) {
            if ((d[i] > d[i - 1] && d[i] > d[i + 1]) || (d[i] < d[i - 1] && d[i] < d[i + 1])) w++;
        }
        return w;
    }

    public boolean isCorrect(int num1, int num2, int output) {
        return output == totalWaviness(num1, num2);
    }
}
