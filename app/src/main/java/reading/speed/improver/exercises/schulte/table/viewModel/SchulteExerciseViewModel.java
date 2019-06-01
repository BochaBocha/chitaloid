package reading.speed.improver.exercises.schulte.table.viewModel;

import android.app.Application;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.AndroidViewModel;
import org.jetbrains.annotations.NotNull;
import reading.speed.improver.exercises.schulte.table.SchulteExercise;
import reading.speed.improver.service.ChitaloidService;

public class SchulteExerciseViewModel extends AndroidViewModel {
    private MutableLiveData<Integer> currentSeconds;
    private MutableLiveData<Integer> currentNumber;
    private MutableLiveData<Float> currentTextSizeCoeff;
    private SchulteExercise schulteExercise;
    private boolean pauseDialogHidden;

    public SchulteExerciseViewModel(@NotNull Application application) {
        super(application);
        ChitaloidService chitaloidService = new ChitaloidService();
        schulteExercise = chitaloidService.createSchulteTableExercise();
        schulteExercise.startStopwatch();
        currentSeconds = schulteExercise.getElapsedSeconds();
        currentTextSizeCoeff = schulteExercise.getTextSizeCoeff();
        currentNumber = schulteExercise.getCurrentNumber();
        pauseDialogHidden = true;
    }

    public MutableLiveData<Float> getCurrentTextSizeCoeff() {
        return currentTextSizeCoeff;
    }

    public MutableLiveData<Integer> getCurrentSeconds() {
        return currentSeconds;
    }

    public MutableLiveData<Integer> getCurrentNumber() {
        return currentNumber;
    }

    public boolean isPauseDialogHidden() {
        return pauseDialogHidden;
    }

    public void setPauseDialogHidden(boolean value) {
        pauseDialogHidden = value;
    }

    public void changeTextSize(final Float textSize) {
        schulteExercise.changeTextSize(textSize);
    }

    public boolean isCurrentNumFound(final int row, final int column) {
        return schulteExercise.isCurrentNumFound(row, column);
    }

    public void increaseCurrentNum() {
        schulteExercise.increaseCurrentNum();
    }

    public MutableLiveData<Integer[][]> getSchulteTable() {
        return schulteExercise.getSchulteTable();
    }

    public void pauseStopwatch() {
        schulteExercise.pauseStopwatch();
    }

    public void startStopwatch() {
        schulteExercise.startStopwatch();
    }

    public void restartExercise() {
        schulteExercise.restartExercise();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        ChitaloidService chitaloidService = new ChitaloidService();
        chitaloidService.invalidateCurrentExercise();
    }
}
