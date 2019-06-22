package reading.speed.improver.exercises.text.appearance.fading;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import reading.speed.improver.exercises.materials.Text;
import reading.speed.improver.service.ChitaloidService;
import reading.speed.improver.utils.formatter.TextFormatter;

import java.util.ArrayList;

public class FadingText {
    private ArrayList<String> fullTextArray;
    private ArrayList<String> trimmedTextArray;
    private int relativePosition;
    private int absolutePosition;
    private int initialTextArraySize;
    private final int DISPLAYED_WORDS = 40;
    private final int WORDS_DELAY = 1;
    private int wordsDelayCount;

    public FadingText() {
        String fullText = getRandomText().content;
        TextFormatter textFormatter = new TextFormatter();
        fullTextArray = textFormatter.splitStringIntoArray(fullText);
        fullTextArray = textFormatter.formatNewLines(fullTextArray);
        relativePosition = 0;
        absolutePosition = 0;
        initialTextArraySize = fullTextArray.size();
        trimFullTextArray();
    }

    public void fadeText() {
        if (wordsDelayCount >= 0) {
            wordsDelayCount--;
            return;
        }
        relativePosition++;
        absolutePosition++;
    }

    public boolean hasCompletelyFaded() {
        return absolutePosition >= initialTextArraySize;
    }

    public SpannableString getFadedText() {
        if (relativePosition >= (trimmedTextArray.size())) {
            relativePosition = 0;
            trimFullTextArray();
        }
        return constructSpannableText();
    }

    private Text getRandomText() {
        ChitaloidService chitaloidService = new ChitaloidService();
        return chitaloidService.getRandomText();
    }

    private SpannableString constructSpannableText() {
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder("");
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.WHITE);
        int endIndex = 0;
        for (int i = 0; i < trimmedTextArray.size(); i++) {
            boolean space = false;
            String word = trimmedTextArray.get(i);
            if (!word.contains("\n")) {
                word = word + " ";
            }
            if (i < relativePosition) {
                int wordLength = word.length();
                endIndex += wordLength;
            }
            stringBuilder.append(word);
        }

        if (endIndex != 0) {
            stringBuilder.setSpan(foregroundColorSpan, 0, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        return SpannableString.valueOf(stringBuilder);
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
}
