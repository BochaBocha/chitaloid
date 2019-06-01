package reading.speed.improver.exercises.text.spaceless;

import reading.speed.improver.exercises.materials.sentence.SentencesClassification;
import reading.speed.improver.service.ChitaloidService;
import reading.speed.improver.utils.formatter.TextFormatter;

public class SpacelessText {
    private String sentence;

    SpacelessText(final SentencesClassification sentencesClassification) {
        sentence = getRandomSentence(sentencesClassification);
        TextFormatter textFormatter = new TextFormatter();
        sentence = textFormatter.makeStringSpaceless(sentence);
        sentence = textFormatter.makeStringSignless(sentence);
        sentence = textFormatter.makeStringLowerCase(sentence);
    }

    public String getSentence(){
        return sentence;
    }

    private String getRandomSentence(final SentencesClassification sentencesClassification) {
        ChitaloidService chitaloidService = new ChitaloidService();
        return chitaloidService.getRandomSentence(sentencesClassification).content;
    }
}
