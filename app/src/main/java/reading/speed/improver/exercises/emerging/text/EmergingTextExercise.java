package reading.speed.improver.exercises.emerging.text;

import androidx.lifecycle.MutableLiveData;
import reading.speed.improver.exercises.ExerciseModel;
import reading.speed.improver.exercises.emerging.text.params.EmergingTextExerciseParams;
import reading.speed.improver.exercises.emerging.text.stopwatch.Stopwatch;
import reading.speed.improver.exercises.emerging.text.stopwatch.StopwatchObserver;

import java.util.UUID;


//TODO: timer and word(!!!) reader
public class EmergingTextExercise implements StopwatchObserver, ExerciseModel {
    private UUID id;
    private String name;
    private Stopwatch stopwatch;
    private Float defaultTextSizeCoeff = 1f;
    private Float defaultSpeed;
    private EmergingText emergingText;

    public MutableLiveData<String> getCurrentText() {
        return currentText;
    }

    private MutableLiveData<String> currentText;
    private MutableLiveData<Boolean> autoScroll;


    public MutableLiveData<Float> getCurrentSpeed() {
        return currentSpeed;
    }

    private MutableLiveData<Float> currentSpeed;
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
        currentSpeed.setValue(1f);
        autoScroll = new MutableLiveData<>();
        autoScroll.setValue(true);
        stopwatch = new Stopwatch();

        stopwatch.registerObserver(this);
        emergingText = new EmergingText();

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

    public void setAutoScrollOption(final Boolean autoScrollOption) {
        autoScroll.setValue(autoScrollOption);
    }

    @Override
    public void update(final int seconds) {
        emergeText();
        currentText.setValue(getEmergedText());
    }

    private void emergeText() {
        emergingText.emergeText();
    }

    private String getEmergedText() {
        return emergingText.getEmergedText();
    }

    public void pauseStopwatch() {
        stopwatch.pause();
    }

    public void startStopwatch() {
        stopwatch.start();
    }


    public void restartExercise() {
        stopwatch.reset();
        stopwatch.start();
        currentText.setValue("");
        emergingText = new EmergingText();
    }
}
