package reading.speed.improver.exercises.emerging.text;

import androidx.lifecycle.MutableLiveData;
import reading.speed.improver.exercises.ExerciseModel;
import reading.speed.improver.exercises.components.stopwatch.Stopwatch;
import reading.speed.improver.exercises.components.stopwatch.StopwatchObserver;
import reading.speed.improver.exercises.emerging.text.params.EmergingTextExerciseParams;

import java.util.UUID;


//TODO: timer and word(!!!) reader
public class EmergingTextExercise implements StopwatchObserver, ExerciseModel {
    private UUID id;
    private String name;
    private Stopwatch stopwatch;
    private Float defaultTextSize = 70f;
    private Float defaultSpeed;

    public MutableLiveData<String> getCurrentText() {
        return currentText;
    }

    private MutableLiveData<String> currentText;
    private MutableLiveData<Boolean> autoScroll;


    public MutableLiveData<Float> getCurrentSpeed() {
        return currentSpeed;
    }

    private MutableLiveData<Float> currentSpeed;
    private MutableLiveData<Float> textSize; // in sp

    public EmergingTextExercise(EmergingTextExerciseParams params) {
        name = params.getName();
        defaultTextSize = params.getDefaultTextSize();
        id = UUID.randomUUID();
        textSize = new MutableLiveData<>();
        textSize.setValue(defaultTextSize);
        currentText = new MutableLiveData<>();
        currentText.setValue("");
        currentSpeed = new MutableLiveData<>();
        currentSpeed.setValue(1f);
        autoScroll = new MutableLiveData<>();
        autoScroll.setValue(true);
        stopwatch = new Stopwatch();

        stopwatch.registerObserver(this);
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public MutableLiveData<Float> getTextSize() {
        return textSize;
    }
    public MutableLiveData<Boolean> getAutoScrollOption() {
        return autoScroll;
    }

    public void changeTextSize(final Float textSize) {
        this.textSize.setValue(textSize);
    }

    public void setAutoScrollOption(final Boolean autoScrollOption){
        autoScroll.setValue(autoScrollOption);
    }

    @Override
    public void update(final int seconds) {
        String text = currentText.getValue();
        currentText.setValue(text + getNextWord());
    }

    private String getNextWord() {
        return "J";
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
    }
}
