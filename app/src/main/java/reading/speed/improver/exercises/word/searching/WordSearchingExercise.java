package reading.speed.improver.exercises.word.searching;

import androidx.lifecycle.MutableLiveData;
import reading.speed.improver.exercises.components.timer.Timer;
import reading.speed.improver.exercises.components.timer.TimerObserver;
import reading.speed.improver.exercises.params.DefaultExerciseParams;
import reading.speed.improver.service.ChitaloidService;

import java.util.ArrayList;
import java.util.UUID;

public class WordSearchingExercise implements TimerObserver {
    private UUID id;
    private String name;
    private Timer timer;
    private Float defaultTextSizeCoeff = 1f;
    private Integer defaultSpeed = 5000; //seconds delay
    private final int DEFAULT_AMOUNT_OF_WORDS = 5;
    private int amountOfWords = DEFAULT_AMOUNT_OF_WORDS;

    private MutableLiveData<String> wantedWord;

    public MutableLiveData<ArrayList<String>> getWords() {
        return words;
    }

    private MutableLiveData<ArrayList<String>> words;
    private MutableLiveData<Integer> currentSpeed;
    private MutableLiveData<Float> textSizeCoeff;

    public WordSearchingExercise(DefaultExerciseParams params) {
        name = params.getName();
        defaultTextSizeCoeff = params.getDefaultTextSizeCoeff();
        id = UUID.randomUUID();

        textSizeCoeff = new MutableLiveData<>();
        textSizeCoeff.setValue(defaultTextSizeCoeff);

        currentSpeed = new MutableLiveData<>();
        currentSpeed.setValue(defaultSpeed);

        wantedWord = new MutableLiveData<>();
        words = new MutableLiveData<>();
        fillWithData();
    }

    public MutableLiveData<String> getWantedWord() {
        return wantedWord;
    }

    public MutableLiveData<Integer> getCurrentSpeed() {
        return currentSpeed; //words in second
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public MutableLiveData<Float> getTextSizeCoeff() {
        return textSizeCoeff;
    }


    public void changeTextSizeCoeff(final Float textSizeCoeff) {
        this.textSizeCoeff.setValue(textSizeCoeff);
    }

    public void changeSpeed(int speed) {
        if (currentSpeed.getValue() != speed) {
            currentSpeed.setValue(speed);
            fillWithData();
        }
    }

    private void fillWithData() {
        ChitaloidService chitaloidService = new ChitaloidService();
        wantedWord.setValue(chitaloidService.getRandomWord().content);
        ArrayList<String> tmpWords = new ArrayList<>();
        for (int i = 0; i < amountOfWords; i++) {
            String tmpWord = chitaloidService.getRandomWord().content;
            if (!tmpWord.equals(wantedWord.getValue())) {
                tmpWords.add(tmpWord);
            }
        }
        this.words.setValue(tmpWords);

    }

    @Override
    public void getTimeExceededNotification() {
        fillWithData();
    }

    public void pauseTimer() {
        timer.removeObserver(this);
        timer.pause();
        timer = null;
    }

    public void startTimer() {
        createTimer();
    }

    private void createTimer() {
        if (timer != null) {
            timer.removeObserver(this);
            timer.pause();
        }
        timer = new Timer(currentSpeed.getValue());
        timer.registerObserver(this);
        timer.start();
    }
}
