package reading.speed.improver.exercises.model;

import androidx.lifecycle.MutableLiveData;
import reading.speed.improver.exercises.components.stopwatch.StopwatchObserver;
import reading.speed.improver.exercises.components.stopwatch.Stopwatch;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import java.util.Random;

public class ExerciseModel implements StopwatchObserver {
    private UUID id;
    private String name;
    private String currentWord;
    private Stopwatch stopwatch;
    private double speed;
    private double textSize;
    private final int START_VALUE = 1;
    private final int END_VALUE = 9;
    private final int TABLE_SIZE = 3;
    int[][] schulteTable;

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

    public void setName(final String name) {
        this.name = name;
    }

    public MutableLiveData<Integer> getCurrentSeconds() {
        return currentSeconds;
    }

    @Override
    public void update(int seconds) {
        this.currentSeconds.setValue(seconds);
    }

    public int[][] getSchulteTable() {
        if (schulteTable == null) {
            schulteTable = new int[TABLE_SIZE][TABLE_SIZE];
            generateSchulteTable();
        }
        return schulteTable;
    }

    private void generateSchulteTable() {
        ArrayList<Integer> avaliableNumbers = new ArrayList<>();
        for (int i = START_VALUE; i <= END_VALUE; i++) {
            avaliableNumbers.add(i);
        }
        Random random = new Random();
        for (int i = 0; i < TABLE_SIZE; i++) {
            for (int j = 0; j < TABLE_SIZE; j++) {
                int randomNumber = random.nextInt((avaliableNumbers.size() - 1));
                schulteTable[i][j]= avaliableNumbers.remove(randomNumber);
            }
        }
    }
}
