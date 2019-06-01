package reading.speed.improver.utils.formatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

//a b c d.
//a b c.

public class SpacesMisplacer {

    public String misplaceSpaces(final String str) {
        ArrayList<String> words = new ArrayList<>(Arrays.asList(str.split("\\s+")));
        int amountOfSpaces = calcAmountOfSpaces(words.size());
        ArrayList<Integer> spacesPositions = caclSpacesPosition(str.length(), amountOfSpaces);
        String strWithoutSpaces = str.replaceAll(" ", "");
        int charPosition = 0;
        StringBuilder misplacedSpacesStr = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (spacesPositions.contains(i)) {
                misplacedSpacesStr.append(" ");
            } else {
                misplacedSpacesStr.append(strWithoutSpaces.charAt(charPosition));
                charPosition++;
            }
        }
        return misplacedSpacesStr.toString();
    }

    private int calcAmountOfSpaces(final int amountOfWords) {
        return amountOfWords - 1;
    }

    private ArrayList<Integer> caclSpacesPosition(final int strLenght, final int amountOfSpaces) {
        ArrayList<Integer> spacesPositions = new ArrayList<>();
        Random random = new Random();
        while (spacesPositions.size() <= amountOfSpaces) {
            int position = random.nextInt(strLenght - 2) + 1;
            if (!spacesPositions.contains(position) && !spacesPositions.contains(position + 1) &&
                    !spacesPositions.contains(position - 1)) {
                spacesPositions.add(position);
            }
        }
        return spacesPositions;
    }
}
