package reading.speed.improver.utils.formatter;

import java.util.ArrayList;
import java.util.Arrays;

public class TextFormatter {
    public String formatNewLines(final String str) {
        String formattedStr = str;
        return formattedStr.replaceAll("\\\\n", "\n");
    }

    public ArrayList<String> formatNewLines(ArrayList<String> words) {
        ArrayList<String> formattedWords = new ArrayList<>();
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            String formattedWord = word.replaceAll("\\\\n", "\n");
            formattedWords.add(formattedWord);
        }
        return formattedWords;
    }

    public ArrayList<String> splitStringIntoArray(final String str) {
        return new ArrayList<>(Arrays.asList(str.split("\\s+")));
    }
}
