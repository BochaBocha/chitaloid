package reading.speed.improver.exercises.materials.sentence;

public enum SentencesClassification {
    SENTENCE_EXTRA_SMALL(1, 25),
    SENTENCE_SMALL(26, 50),
    SENTENCE_MEDIUM(51, 70),
    SENTENCE_LARGE(71, 90),
    SENTENCE_EXTRA_LARGE(90, 1000);

    private int minAmountOfLetters;
    private int maxAmountOfLetters;

    SentencesClassification(final int minAmountOfLetters, final int maxAmountOfLetters) {
        this.minAmountOfLetters = minAmountOfLetters;
        this.maxAmountOfLetters = maxAmountOfLetters;
    }

    public int getMinAmountOfLetters() {
        return minAmountOfLetters;
    }

    public int getMaxAmountOfLetters() {
        return maxAmountOfLetters;
    }

}
