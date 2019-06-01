package reading.speed.improver.exercises.text.misplaced.spaces;

import reading.speed.improver.exercises.materials.sentence.SentencesClassification;
import reading.speed.improver.service.ChitaloidService;
import reading.speed.improver.utils.formatter.TextFormatter;

public class MisplacedSpacesText {
    private String sentence;

    MisplacedSpacesText(final SentencesClassification sentencesClassification) {
        sentence = getRandomSentence(sentencesClassification);
        TextFormatter textFormatter = new TextFormatter();
        sentence = textFormatter.misplaceSpaces(sentence);
    }

    public String getSentence(){
        return sentence;
    }

    private String getRandomSentence(final SentencesClassification sentencesClassification) {
        ChitaloidService chitaloidService = new ChitaloidService();
        return chitaloidService.getRandomSentence(sentencesClassification).content;
    }
}
