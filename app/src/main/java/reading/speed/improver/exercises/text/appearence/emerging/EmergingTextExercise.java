package reading.speed.improver.exercises.text.appearence.emerging;

import androidx.lifecycle.MutableLiveData;
import reading.speed.improver.exercises.text.appearence.params.EmergingTextExerciseParams;
import reading.speed.improver.exercises.text.appearence.timer.Timer;
import reading.speed.improver.exercises.text.appearence.timer.TimerObserver;

import java.util.UUID;

public class EmergingTextExercise implements TimerObserver {
    private UUID id;
    private String name;
    private Timer timer;
    private Float defaultTextSizeCoeff = 1f;
    private Integer defaultSpeed = 60; //words in minute
    private EmergingText emergingText;

    private MutableLiveData<String> currentText;
    private MutableLiveData<Boolean> autoScroll;
    private MutableLiveData<Integer> currentSpeed;
    private MutableLiveData<Float> textSizeCoeff;

    public EmergingTextExercise(EmergingTextExerciseParams params) {
        name = params.getName();
        defaultTextSizeCoeff = params.getDefaultTextSizeCoeff();
        id = UUID.randomUUID();
        textSizeCoeff = new MutableLiveData<>();
        textSizeCoeff.setValue(defaultTextSizeCoeff);
        currentText = new MutableLiveData<>();
        currentText.setValue("");
        currentSpeed = new MutableLiveData<>();
        currentSpeed.setValue(defaultSpeed);
        autoScroll = new MutableLiveData<>();
        autoScroll.setValue(true);
        timer = new Timer(calcMillis());

        timer.registerObserver(this);
        emergingText = new EmergingText();
    }

    public MutableLiveData<String> getCurrentText() {
        return currentText;
    }

    public MutableLiveData<Integer> getCurrentSpeed() {
        return currentSpeed;
    } //words in second

    private long calcMillis() {
        return 1000 / Math.round(currentSpeed.getValue()/60);
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

    public MutableLiveData<Boolean> getAutoScrollOption() {
        return autoScroll;
    }

    public void changeTextSizeCoeff(final Float textSizeCoeff) {
        this.textSizeCoeff.setValue(textSizeCoeff);
    }

    public void setSpeed(int speed) {
        currentSpeed.setValue(speed);
        timer.setDelayMillis(calcMillis());
    }

    public void setAutoScrollOption(final Boolean autoScrollOption) {
        autoScroll.setValue(autoScrollOption);
    }

    @Override
    public void getTimeExceededNotification() {
        if(emergingText.hasCompletelyEmerged()){
            emergingText = new EmergingText();
        }
        currentText.setValue(emergingText.getEmergedText());
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
        currentText.setValue("");
        emergingText = new EmergingText();
    }
}
