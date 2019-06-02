package reading.speed.improver.exercises.materials.reader.buffered;

import reading.speed.improver.exercises.materials.Word;
import reading.speed.improver.service.ChitaloidService;

import java.util.ArrayList;

public class BufferedDBWordReader {
    private ArrayList<Word> words;
    private int amountOfLetters;
    private final int BUFFER_SIZE = 20;

    public BufferedDBWordReader(final int amountOfLetters) {
        this.amountOfLetters = amountOfLetters;
        words = readWordsFromDB();
    }

    private ArrayList<Word> readWordsFromDB() {
        ArrayList<Word> words = new ArrayList<>();
        for (int i = 0; i < BUFFER_SIZE; i++) {
            words.add(getRandomWord());
        }
        return words;
    }

    private Word getRandomWord() {
        ChitaloidService chitaloidService = new ChitaloidService();
        return chitaloidService.getRandomWord(amountOfLetters);
    }

    public Word getWord() {
        if (words.size() == 0) {
            words = readWordsFromDB();
        }
        return words.remove(0);
    }
}
