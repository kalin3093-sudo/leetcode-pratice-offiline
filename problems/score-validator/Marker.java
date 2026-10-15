import java.util.*;

class Marker {
    public int[] scoreValidator(String[] events) {
        int score = 0;
        int counter = 0;
        for (String e : events) {
            if (counter == 10)
                break;
            switch (e) {
                case "1": score += 1; break;
                case "2": score += 2; break;
                case "3": score += 3; break;
                case "4": score += 4; break;
                case "6": score += 6; break;
                case "W": counter++; break;
                case "WD":
                case "NB": score += 1; break;
                default: break;
            }
        }
        return new int[] { score, counter };
    }

    public boolean isCorrect(String[] events, int[] output) {
        int[] expected = scoreValidator(events);
        if (output == null || output.length != expected.length)
            return false;
        for (int i = 0; i < expected.length; i++)
            if (expected[i] != output[i])
                return false;
        return true;
    }
}
