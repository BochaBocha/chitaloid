package reading.speed.improver.exercises.text.misplaced.spaces;

import androidx.lifecycle.MutableLiveData;
import reading.speed.improver.exercises.params.DefaultExerciseParams;
import reading.speed.improver.service.SentencesClassificationTransitionService;

import java.util.UUID;

public class MisplacedSpacesExercise {
    private UUID id;
    private String name;
    private Float defaultTextSizeCoeff;
    private MisplacedSpacesText misplacedSpacesText;
    private final int SENTENCES_STEP = 20;
    private int currentStep;
    private int amountOfSentences;
    private SentencesClassificationTransitionService sentenceTransitionService;

    private MutableLiveData<String> currentText;
    private MutableLiveData<Float> textSizeCoeff;

    public MisplacedSpacesExercise(final DefaultExerciseParams params) {
        name = params.getName();
        defaultTextSizeCoeff = params.getDefaultTextSizeCoeff();
        id = UUID.randomUUID();
        textSizeCoeff = new MutableLiveData<>();
        textSizeCoeff.setValue(defaultTextSizeCoeff);
        amountOfSentences = 1;
        currentStep = SENTENCES_STEP;
        sentenceTransitionService = new SentencesClassificationTransitionService();
        misplacedSpacesText = new MisplacedSpacesText(sentenceTransitionService.getCurrentSentenceClassification());
        currentText = new MutableLiveData<>();
        currentText.setValue(misplacedSpacesText.getSentence());
    }

    public void makeNewText() {
        currentStep--;
        if (currentStep == 0) {
            sentenceTransitionService.getNextSentenceClassification();
            currentStep = SENTENCES_STEP;
        }
        misplacedSpacesText = new MisplacedSpacesText(sentenceTransitionService.getCurrentSentenceClassification());
        currentText.setValue(misplacedSpacesText.getSentence());
    }


    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void restart(){
        sentenceTransitionService = new SentencesClassificationTransitionService();
        misplacedSpacesText = new MisplacedSpacesText(sentenceTransitionService.getCurrentSentenceClassification());
        currentText.setValue(misplacedSpacesText.getSentence());
    }

    public MutableLiveData<Float> getTextSizeCoeff() {
        return textSizeCoeff;
    }

    public void changeTextSizeCoeff(final Float textSizeCoeff) {
        this.textSizeCoeff.setValue(textSizeCoeff);
    }

    public MutableLiveData<String> getCurrentText() {
        return currentText;
    }
}
