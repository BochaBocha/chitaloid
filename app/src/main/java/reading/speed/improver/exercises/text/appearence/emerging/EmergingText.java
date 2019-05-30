package reading.speed.improver.exercises.text.appearence.emerging;

import reading.speed.improver.exercises.materials.Text;
import reading.speed.improver.service.ChitaloidService;
import reading.speed.improver.utils.formatter.TextFormatter;

import java.util.ArrayList;

public class EmergingText {
    private ArrayList<String> fullTextArray;
    private ArrayList<String> trimmedTextArray;
    private int relativePosition;
    private int absolutePosition;
    private int initialTextArraySize;
    private final int DISPLAYED_WORDS = 65;
    private final int WORDS_DELAY = 3;
    private int wordsDelayCount;

    EmergingText() {
        String fullText = getRandomText().content;
        TextFormatter textFormatter = new TextFormatter();
        fullTextArray = textFormatter.splitStringIntoArray(fullText);
        relativePosition = 0;
        absolutePosition = 0;
        initialTextArraySize = fullTextArray.size();
        trimFullTextArray();
    }

    private Text getRandomText() {
        ChitaloidService chitaloidService = new ChitaloidService();
        return chitaloidService.getRandomText();
    }

    private void trimFullTextArray() {
        wordsDelayCount = WORDS_DELAY;
        if (fullTextArray.size() <= DISPLAYED_WORDS) {
            trimmedTextArray = fullTextArray;
            return;
        }
        trimmedTextArray = new ArrayList<>(fullTextArray.subList(0, DISPLAYED_WORDS));
        fullTextArray = new ArrayList<>(fullTextArray.subList(DISPLAYED_WORDS, fullTextArray.size()));
    }

    public String getEmergedText() {
        emergeText();
        if (relativePosition >= (trimmedTextArray.size())) {
            relativePosition = 0;
            trimFullTextArray();
        }
        ArrayList<String> emergedWords = new ArrayList<>(trimmedTextArray.subList(0, relativePosition + 1));
        return constructText(emergedWords);
    }

    private String constructText(ArrayList<String> words) {
        StringBuilder text = new StringBuilder();
        for (String word : words) {
            if (word.contains("\\n")) {
                text.append(word);
            } else {
                text.append(word).append(" ");
            }
        }
        TextFormatter textFormatter = new TextFormatter();
        return textFormatter.formatNewLines(text.toString());
    }

    private void emergeText() {
        if (relativePosition == (trimmedTextArray.size() - 1) && wordsDelayCount >= 0) {
            wordsDelayCount--;
            return;
        }
        relativePosition++;
        absolutePosition++;
    }

    public boolean hasCompletelyEmerged() {
        return absolutePosition >= initialTextArraySize;
    }
}
