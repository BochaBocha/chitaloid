package reading.speed.improver.exercises.model;

import androidx.lifecycle.MutableLiveData;
import reading.speed.improver.exercises.components.stopwatch.StopwatchObserver;
import reading.speed.improver.exercises.components.stopwatch.Stopwatch;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class ExerciseModel implements StopwatchObserver {
    private UUID id;
    private String name;
    private Stopwatch stopwatch;
    private double speed;
    private double textSize;
    private final int START_VALUE = 1;
    private final int END_VALUE = 9;
    private final int TABLE_SIZE = 3;
    private int currentNum = START_VALUE;

    private MutableLiveData<Integer> elapsedSeconds;
    private MutableLiveData<Integer[][]> schulteTable;

    public ExerciseModel() {
        id = UUID.randomUUID();
        name = "Найди слово";
        speed = 1;
        textSize = 1;
        elapsedSeconds = new MutableLiveData<>();
        elapsedSeconds.setValue(0);
        stopwatch = new Stopwatch();
        stopwatch.registerObserver(this);
        stopwatch.start();
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public MutableLiveData<Integer> getElapsedSeconds() {
        return elapsedSeconds;
    }

    @Override
    public void update(int seconds) {
        this.elapsedSeconds.setValue(seconds);
    }

    public MutableLiveData<Integer[][]> getSchulteTable() {
        if (schulteTable == null) {
            schulteTable = new MutableLiveData<>();
            schulteTable.setValue(new Integer[TABLE_SIZE][TABLE_SIZE]);
            generateSchulteTable();
        }
        return schulteTable;
    }

    public boolean isCurrentNumFound(int number) {
        return currentNum == number;
    }

    private void generateSchulteTable() {
        ArrayList<Integer> avaliableNumbers = new ArrayList<>();
        for (int i = START_VALUE; i <= END_VALUE; i++) {
            avaliableNumbers.add(i);
        }
        Random random = new Random();
        Integer[][] tmpSchulteTable = new Integer[TABLE_SIZE][TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++) {
            for (int j = 0; j < TABLE_SIZE; j++) {
                int randomNumber = random.nextInt((avaliableNumbers.size()));

                tmpSchulteTable[i][j] = avaliableNumbers.remove(randomNumber);
            }
        }
        schulteTable.setValue(tmpSchulteTable);
    }

    public void pauseStopwatch() {
        stopwatch.pause();
    }

    public void startStopwatch() {
        stopwatch.start();
    }

    public void increaseCurrentNum() {
        ++currentNum;
    }

    public void restartExercise(){
        stopwatch.reset();
        stopwatch.start();
        currentNum = START_VALUE;
        generateSchulteTable();
    }
}
