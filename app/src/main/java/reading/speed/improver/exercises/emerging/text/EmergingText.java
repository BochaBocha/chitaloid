package reading.speed.improver.exercises.emerging.text;

import reading.speed.improver.exercises.materials.Text;
import reading.speed.improver.service.ChitaloidService;

import java.util.ArrayList;
import java.util.Arrays;

public class EmergingText {
    private ArrayList<String> fullTextArray;
    private int currentPosition;

    EmergingText() {
        String fullText = getRandomText().content;
        fullTextArray = splitStringIntoArray(fullText);
        currentPosition = 0;
    }

    private Text getRandomText() {
        ChitaloidService chitaloidService = new ChitaloidService();
        return chitaloidService.getRandomText();
    }

    public String getEmergedText() {
        return fullTextArray.subList(0, currentPosition).toString();
    }

    public void emergeText() {
        currentPosition++;
    }

    private ArrayList<String> splitStringIntoArray(String str) {
        return new ArrayList<>(Arrays.asList(str.split("\\s+")));
    }
}
