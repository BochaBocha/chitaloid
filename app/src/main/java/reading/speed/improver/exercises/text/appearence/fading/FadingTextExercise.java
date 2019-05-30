package reading.speed.improver.exercises.text.appearence.fading;

import android.text.SpannableString;
import androidx.lifecycle.MutableLiveData;
import reading.speed.improver.exercises.text.appearence.params.EmergingTextExerciseParams;
import reading.speed.improver.exercises.text.appearence.timer.Timer;
import reading.speed.improver.exercises.text.appearence.timer.TimerObserver;

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

    public FadingTextExercise(EmergingTextExerciseParams params) {
        name = params.getName();
        defaultTextSizeCoeff = params.getDefaultTextSizeCoeff();
        id = UUID.randomUUID();
        textSizeCoeff = new MutableLiveData<>();
        textSizeCoeff.setValue(defaultTextSizeCoeff);
        currentText = new MutableLiveData<>();
        currentText.setValue(new SpannableString(""));
        currentSpeed = new MutableLiveData<>();
        currentSpeed.setValue(defaultSpeed);
        timer = new Timer(calcMillis());

        timer.registerObserver(this);
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
        timer.setDelayMillis(calcMillis());
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
        timer.pause();
    }

    public void startTimer() {
        timer.start();
    }

    public void restartExercise() {
        timer.reset();
        timer.start();
        currentText.setValue(new SpannableString(""));
        fadingText = new FadingText();
    }

    private long calcMillis() {
        return 1000 / Math.round(currentSpeed.getValue() / 60);
    }
}
