package reading.speed.improver.exercises.text.appearance.fading;

import android.text.SpannableString;
import androidx.lifecycle.MutableLiveData;
import reading.speed.improver.exercises.params.DefaultExerciseParams;
import reading.speed.improver.exercises.components.timer.Timer;
import reading.speed.improver.exercises.components.timer.TimerObserver;

import java.util.UUID;

public class FadingTextExercise implements TimerObserver {
    private UUID id;
    private String name;
    private Timer timer;
    private Float defaultTextSizeCoeff = 1f;
    private Integer defaultSpeed = 60; //words in minute
    private FadingText fadingText;


    private MutableLiveData<SpannableString> currentText;
    private MutableLiveData<Integer> currentSpeed;
    private MutableLiveData<Float> textSizeCoeff;

    public FadingTextExercise(DefaultExerciseParams params) {
        name = params.getName();
        defaultTextSizeCoeff = params.getDefaultTextSizeCoeff();
        id = UUID.randomUUID();
        textSizeCoeff = new MutableLiveData<>();
        textSizeCoeff.setValue(defaultTextSizeCoeff);
        currentText = new MutableLiveData<>();
        currentText.setValue(new SpannableString(""));
        currentSpeed = new MutableLiveData<>();
        currentSpeed.setValue(defaultSpeed);
        fadingText = new FadingText();
    }

    public MutableLiveData<Integer> getCurrentSpeed() {
        return currentSpeed;
    }

    public MutableLiveData<SpannableString> getCurrentText() {
        return currentText;
    }

    public MutableLiveData<Float> getTextSizeCoeff() {
        return textSizeCoeff;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }


    public void changeTextSizeCoeff(final Float textSizeCoeff) {
        this.textSizeCoeff.setValue(textSizeCoeff);
    }

    public void setSpeed(int speed) {
        currentSpeed.setValue(speed);
    }

    @Override
    public void getTimeExceededNotification() {
        if (fadingText.hasCompletelyFaded()) {
            fadingText = new FadingText();
        }
        currentText.setValue(fadingText.getFadedText());
        fadingText.fadeText();
    }

    public void pauseTimer() {
        timer.removeObserver(this);
        timer.pause();
        timer = null;
    }

    public void startTimer() {
        createTimer();
    }

    public void restartExercise() {
        createTimer();
        currentText.setValue(new SpannableString(""));
        fadingText = new FadingText();
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

    private long calcMillis() {
        return 1000 / Math.round(currentSpeed.getValue() / 60);
    }
}
