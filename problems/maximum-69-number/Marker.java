import java.util.*;
class Marker {
    public int maximum69Number(int num) {
        String s = String.valueOf(num);
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '6') {
                arr[i] = '9';
                break;
            }
        }
        return Integer.parseInt(new String(arr));
    }

    public boolean isCorrect(int num, int output) {
        return maximum69Number(num) == output;
    }
}