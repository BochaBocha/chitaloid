package reading.speed.improver.exercises.word.appearence;

import androidx.lifecycle.MutableLiveData;
import reading.speed.improver.exercises.components.timer.Timer;
import reading.speed.improver.exercises.components.timer.TimerObserver;
import reading.speed.improver.exercises.params.DefaultExerciseParams;

import java.util.UUID;

public class EmergingWordsExercise implements TimerObserver {
    private UUID id;
    private String name;
    private Timer timer;
    private Float defaultTextSizeCoeff = 1f;
    private Integer defaultSpeed = 60; //words in minute
    private Integer defaultAmountOfLetters = 3;
    private EmergingWords emergingWords;

    private MutableLiveData<String> currentText;
    private MutableLiveData<Integer> amountOfLetters;
    private MutableLiveData<Integer> currentSpeed;
    private MutableLiveData<Float> textSizeCoeff;

    public EmergingWordsExercise(DefaultExerciseParams params) {
        name = params.getName();
        defaultTextSizeCoeff = params.getDefaultTextSizeCoeff();
        id = UUID.randomUUID();

        textSizeCoeff = new MutableLiveData<>();
        textSizeCoeff.setValue(defaultTextSizeCoeff);

        currentSpeed = new MutableLiveData<>();
        currentSpeed.setValue(defaultSpeed);

        amountOfLetters = new MutableLiveData<>();
        amountOfLetters.setValue(defaultAmountOfLetters);
        emergingWords = new EmergingWords(defaultAmountOfLetters);

        currentText = new MutableLiveData<>();
        currentText.setValue(emergingWords.getWord());
    }

    public MutableLiveData<String> getCurrentText() {
        return currentText;
    }

    public MutableLiveData<Integer> getCurrentSpeed() {
        return currentSpeed;
    } //words in second

    private long calcMillis() {
        return 1000 / Math.round(currentSpeed.getValue() / 60);
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

    public MutableLiveData<Integer> getAmountOfLetters() {
        return amountOfLetters;
    }

    public void changeTextSizeCoeff(final Float textSizeCoeff) {
        this.textSizeCoeff.setValue(textSizeCoeff);
    }

    public void setSpeed(int speed) {
        currentSpeed.setValue(speed);
    }

    public void setAmountOfLetters(final Integer amountOfLetters) {
        this.amountOfLetters.setValue(amountOfLetters);
        emergingWords = new EmergingWords(amountOfLetters);
    }

    @Override
    public void getTimeExceededNotification() {
        currentText.setValue(emergingWords.getWord());
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
        timer = new Timer(calcMillis());
        timer.registerObserver(this);
        timer.start();
    }
}
