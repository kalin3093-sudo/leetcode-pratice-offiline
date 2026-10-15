import java.util.*;
class Marker {
    public String generateTag(String caption) {
        String[] words = caption.split("\\s+");
        StringBuilder sb = new StringBuilder("#");
        for (int i = 0; i < words.length; i++) {
            String w = words[i].toLowerCase();
            if (i > 0) w = Character.toUpperCase(w.charAt(0)) + w.substring(1);
            sb.append(w);
        }
        String tag = sb.toString();
        tag = tag.replaceAll("[^a-zA-Z#]", "");
        if (tag.length() > 100) tag = tag.substring(0, 100);
        return tag;
    }
    public boolean isCorrect(String caption, String output) {
        return generateTag(caption).equals(output);
    }
}