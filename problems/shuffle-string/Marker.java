import java.util.*;

class Marker {
    public String restoreString(String s, int[] indices) {
        char[] res = new char[s.length()];
        for (int i = 0; i < s.length(); i++)
            res[indices[i]] = s.charAt(i);
        return new String(res);
    }

    public boolean isCorrect(String s, int[] indices, String output) {
        return restoreString(s, indices).equals(output);
    }
}
