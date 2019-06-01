package reading.speed.improver.service;

import reading.speed.improver.exercises.materials.sentence.SentencesClassification;

import java.util.ArrayList;

public class SentencesClassificationTransitionService {
    private ArrayList<SentencesClassification> sentencesClassifications;
    private int currentPosition;

    public SentencesClassificationTransitionService() {
        currentPosition = 0;
        sentencesClassifications = new ArrayList<>();
        sentencesClassifications.add(SentencesClassification.SENTENCE_EXTRA_SMALL);
        sentencesClassifications.add(SentencesClassification.SENTENCE_SMALL);
        sentencesClassifications.add(SentencesClassification.SENTENCE_MEDIUM);
        sentencesClassifications.add(SentencesClassification.SENTENCE_LARGE);
        sentencesClassifications.add(SentencesClassification.SENTENCE_EXTRA_LARGE);
    }

    public SentencesClassification getCurrentSentenceClassification() {
        return sentencesClassifications.get(currentPosition);
    }

    public SentencesClassification getNextSentenceClassification() {
        if (currentPosition < sentencesClassifications.size() - 1) {
            currentPosition++;
        }
        return sentencesClassifications.get(currentPosition);
    }
}
