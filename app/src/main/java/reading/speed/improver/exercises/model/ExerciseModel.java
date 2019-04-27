package reading.speed.improver.exercises.model;

import androidx.lifecycle.MutableLiveData;
import reading.speed.improver.exercises.components.stopwatch.StopwatchObserver;
import reading.speed.improver.exercises.components.stopwatch.Stopwatch;

import java.util.UUID;

public class ExerciseModel  implements StopwatchObserver {
    private UUID id;
    private String name;
    private String currentWord;
    private Stopwatch stopwatch;
    private double speed;
    private double textSize;

    private MutableLiveData<Integer> currentSeconds;

    public ExerciseModel() {
        id = UUID.randomUUID();
        name = "Найди слово";
        speed = 1;
        textSize = 1;
        currentSeconds = new MutableLiveData<>();
        currentSeconds.setValue(0);
        stopwatch = new Stopwatch();
        stopwatch.registerObserver(this);
        stopwatch.start();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MutableLiveData<Integer> getCurrentSeconds() {
        return currentSeconds;
    }

    @Override
    public void update(int seconds) {
        this.currentSeconds.setValue(seconds);
    }
}
