package reading.speed.improver.exercises.word.appearence;

import reading.speed.improver.exercises.materials.reader.buffered.BufferedDBWordReader;

public class EmergingWords {
    private int amountOfLetters;
    private BufferedDBWordReader bufferedDBWordReader;

    EmergingWords(final int amountOfLetters) {
        this.amountOfLetters = amountOfLetters;
        bufferedDBWordReader = new BufferedDBWordReader(amountOfLetters);
    }

    public String getWord(){
        return bufferedDBWordReader.getWord().content;
    }
}
