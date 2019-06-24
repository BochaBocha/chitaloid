package reading.speed.improver.exercises.word.appearance.viewModel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import org.jetbrains.annotations.NotNull;
import reading.speed.improver.exercises.word.appearance.EmergingWordsExercise;
import reading.speed.improver.service.ChitaloidService;

public class EmergingWordsExerciseViewModel extends AndroidViewModel {
    private MutableLiveData<Integer> currentSpeed;
    private MutableLiveData<String> currentText;
    private MutableLiveData<Float> currentTextSizeCoeff;
    private MutableLiveData<Integer> amountOfLetters;
    private EmergingWordsExercise emergingWordsExercise;
    private boolean pauseDialogHidden;
    ...
        
    public EmergingWordsExerciseViewModel(@NotNull Application application) {
        super(application);
        ChitaloidService chitaloidService = new ChitaloidService();
        emergingWordsExercise = chitaloidService.createEmergingWordsExercise();
        emergingWordsExercise.startTimer();
        currentTextSizeCoeff = emergingWordsExercise.getTextSizeCoeff();
        currentSpeed = emergingWordsExercise.getCurrentSpeed();
        currentText = emergingWordsExercise.getCurrentText();
        amountOfLetters = emergingWordsExercise.getAmountOfLetters();
        pauseDialogHidden = true;
    }

    public MutableLiveData<Float> getCurrentTextSizeCoeff() {
        return currentTextSizeCoeff;
    }

    public MutableLiveData<Integer> getCurrentSpeed() {
        return currentSpeed;
    }

    public MutableLiveData<String> getCurrentText() {
        return currentText;
    }

    public MutableLiveData<Integer> getAmountOfLetters() {
        return amountOfLetters;
    }

    public boolean isPauseDialogHidden() {
        return pauseDialogHidden;
    }

    public void setPauseDialogHidden(boolean value) {
        pauseDialogHidden = value;
    }

    public void changeTextSizeCoeff(final Float textSizeCoeff) {
        emergingWordsExercise.changeTextSizeCoeff(textSizeCoeff);
    }

    public void setAmountOfLetters(final Integer amountOfLetters) {
        emergingWordsExercise.setAmountOfLetters(amountOfLetters);
    }

    public void setSpeed(final int speed){
        emergingWordsExercise.setSpeed(speed);
    }

    public void pauseStopwatch() {
        emergingWordsExercise.pauseTimer();
    }

    public void startStopwatch() {
        emergingWordsExercise.startTimer();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        ChitaloidService chitaloidService = new ChitaloidService();
        chitaloidService.invalidateCurrentExercise();
    }
}
