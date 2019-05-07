package reading.speed.improver.exercises.viewmodel;

import android.app.Application;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.AndroidViewModel;
import org.jetbrains.annotations.NotNull;
import reading.speed.improver.exercises.model.ExerciseModel;

public class ExerciseViewModel extends AndroidViewModel {
    private MutableLiveData<Integer> currentSeconds;
    private ExerciseModel exerciseModel;
    private final int START_VALUE = 1;
    private final int END_VALUE = 9;
    private final int TABLE_SIZE = 3;
    int[][] schulteTable = new int[TABLE_SIZE][TABLE_SIZE];

    public ExerciseViewModel(@NotNull Application application) {
        super(application);
        exerciseModel = new ExerciseModel();
        currentSeconds = exerciseModel.getElapsedSeconds();
    }

    public MutableLiveData<Integer> getCurrentSeconds() {
        return currentSeconds;
    }


    public boolean isCurrentNumFound(int number) {
        return exerciseModel.isCurrentNumFound(number);
    }

    public void increaseCurrentNum(){
        exerciseModel.increaseCurrentNum();
    }

    public MutableLiveData<Integer[][]> getSchulteTable(){
       return exerciseModel.getSchulteTable();
    }

    public void onCleared() {
        super.onCleared();
    }

    public void pauseStopwatch(){
        exerciseModel.pauseStopwatch();
    }

    public void startStopwatch(){
        exerciseModel.startStopwatch();
    }

    public void restartExercise(){
        exerciseModel.restartExercise();
    }
}
