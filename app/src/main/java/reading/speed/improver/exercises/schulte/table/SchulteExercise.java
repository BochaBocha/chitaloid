package reading.speed.improver.exercises.schulte.table;

import androidx.lifecycle.MutableLiveData;
import reading.speed.improver.exercises.components.stopwatch.StopwatchObserver;
import reading.speed.improver.exercises.components.stopwatch.Stopwatch;
import reading.speed.improver.exercises.schulte.table.params.SchulteTableExerciseParams;
import reading.speed.improver.service.ChitaloidService;
import reading.speed.improver.statistic.Statistic;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class SchulteExercise implements StopwatchObserver {
    private UUID id;
    private String name;
    private Stopwatch stopwatch;
    private Float defaultTextSizeCoeff;
    private int tableSize;
    private int startValue;
    private int endValue;

    private MutableLiveData<Integer> elapsedSeconds;

    public MutableLiveData<Integer> getCurrentNumber() {
        return currentNumber;
    }

    private MutableLiveData<Integer> currentNumber;
    private MutableLiveData<Integer[][]> schulteTable;
    private MutableLiveData<Float> textSizeCoeff;

    public SchulteExercise(SchulteTableExerciseParams params) {
        name = params.getName();
        tableSize = params.getTableSize();
        defaultTextSizeCoeff = params.getDefaultTextSizeCoeff();
        startValue = 1;
        endValue = tableSize * tableSize;
        id = UUID.randomUUID();
        textSizeCoeff = new MutableLiveData<>();
        textSizeCoeff.setValue(defaultTextSizeCoeff);
        currentNumber = new MutableLiveData<>();
        currentNumber.setValue(startValue);
        elapsedSeconds = new MutableLiveData<>();
        elapsedSeconds.setValue(0);
        stopwatch = new Stopwatch();
        stopwatch.registerObserver(this);
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

    public MutableLiveData<Float> getTextSizeCoeff() {
        return textSizeCoeff;
    }

    public void changeTextSize(Float textSize) {
        this.textSizeCoeff.setValue(textSize);
    }

    @Override
    public void update(int seconds) {
        this.elapsedSeconds.setValue(seconds);
    }

    public MutableLiveData<Integer[][]> getSchulteTable() {
        if (schulteTable == null) {
            schulteTable = new MutableLiveData<>();
            schulteTable.setValue(new Integer[tableSize][tableSize]);
            generateSchulteTable();
        }
        return schulteTable;
    }

    public boolean isCurrentNumFound(int row, int column) {
        return (schulteTable.getValue())[row][column] == currentNumber.getValue();
    }

    private void generateSchulteTable() {
        ArrayList<Integer> avaliableNumbers = new ArrayList<>();
        for (int i = startValue; i <= endValue; i++) {
            avaliableNumbers.add(i);
        }
        Random random = new Random();
        Integer[][] tmpSchulteTable = new Integer[tableSize][tableSize];
        for (int i = 0; i < tableSize; i++) {
            for (int j = 0; j < tableSize; j++) {
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
        int currentNumberValue = currentNumber.getValue();
        if (currentNumberValue == endValue) {
            saveStatistic();
            restartExercise();
        } else {
            currentNumber.setValue(++currentNumberValue);
        }
    }
    private void saveStatistic(){
        ChitaloidService chitaloidService = new ChitaloidService();
        Statistic statistic = chitaloidService.createStatistic(name, elapsedSeconds.getValue());
        chitaloidService.insertStatistic(statistic);
    }

    public void restartExercise() {
        stopwatch.reset();
        stopwatch.start();
        currentNumber.setValue(startValue);
        generateSchulteTable();
    }
}
