package reading.speed.improver.exercises.text.spaceless;

import androidx.lifecycle.MutableLiveData;
import reading.speed.improver.exercises.params.DefaultExerciseParams;
import reading.speed.improver.service.SentencesClassificationTransitionService;

import java.util.UUID;

public class SpacelessTextExercise {
    private UUID id;
    private String name;
    private Float defaultTextSizeCoeff;
    private SpacelessText spacelessText;
    private final int SENTENCES_STEP = 20;
    private int currentStep;
    private int amountOfSentences;
    private SentencesClassificationTransitionService sentenceTransitionService;

    private MutableLiveData<String> currentText;
    private MutableLiveData<Float> textSizeCoeff;

    public SpacelessTextExercise(final DefaultExerciseParams params) {
        name = params.getName();
        defaultTextSizeCoeff = params.getDefaultTextSizeCoeff();
        id = UUID.randomUUID();
        textSizeCoeff = new MutableLiveData<>();
        textSizeCoeff.setValue(defaultTextSizeCoeff);
        amountOfSentences = 1;
        currentStep = SENTENCES_STEP;
        sentenceTransitionService = new SentencesClassificationTransitionService();
        spacelessText = new SpacelessText(sentenceTransitionService.getCurrentSentenceClassification());
        currentText = new MutableLiveData<>();
        currentText.setValue(spacelessText.getSentence());
    }

    public void makeNewText() {
        currentStep--;
        if (currentStep == 0) {
            sentenceTransitionService.getNextSentenceClassification();
            currentStep = SENTENCES_STEP;
        }
        spacelessText = new SpacelessText(sentenceTransitionService.getCurrentSentenceClassification());
        currentText.setValue(spacelessText.getSentence());
    }


    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void restart(){
        sentenceTransitionService = new SentencesClassificationTransitionService();
        spacelessText = new SpacelessText(sentenceTransitionService.getCurrentSentenceClassification());
        currentText.setValue(spacelessText.getSentence());
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
