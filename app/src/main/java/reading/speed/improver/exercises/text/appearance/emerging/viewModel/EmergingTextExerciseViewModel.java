package reading.speed.improver.exercises.text.appearance.emerging.viewModel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import org.jetbrains.annotations.NotNull;
import reading.speed.improver.exercises.text.appearance.emerging.EmergingTextExercise;
import reading.speed.improver.service.ChitaloidService;

public class EmergingTextExerciseViewModel extends AndroidViewModel {
    private MutableLiveData<Integer> currentSpeed;
    private MutableLiveData<String> currentText;
    private MutableLiveData<Float> currentTextSizeCoeff;
    private MutableLiveData<Boolean> autoScrollOption;
    private EmergingTextExercise emergingTextExercise;
    private boolean pauseDialogHidden;

    public EmergingTextExerciseViewModel(@NotNull Application application) {
        super(application);
        ChitaloidService chitaloidService = new ChitaloidService();
        emergingTextExercise = chitaloidService.createEmergingTextExercise();
        emergingTextExercise.startTimer();
        currentTextSizeCoeff = emergingTextExercise.getTextSizeCoeff();
        currentSpeed = emergingTextExercise.getCurrentSpeed();
        currentText = emergingTextExercise.getCurrentText();
        autoScrollOption = emergingTextExercise.getAutoScrollOption();
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

    public MutableLiveData<Boolean> getAutoScrollOption() {
        return autoScrollOption;
    }

    public boolean isPauseDialogHidden() {
        return pauseDialogHidden;
    }

    public void setPauseDialogHidden(boolean value) {
        pauseDialogHidden = value;
    }

    public void changeTextSizeCoeff(final Float textSizeCoeff) {
        emergingTextExercise.changeTextSizeCoeff(textSizeCoeff);
    }

    public void setAutoScrollOption(final Boolean autoScrollOption) {
        emergingTextExercise.setAutoScrollOption(autoScrollOption);
    }

    public void setSpeed(final int speed){
        emergingTextExercise.setSpeed(speed);
    }

    public void pauseStopwatch() {
        emergingTextExercise.pauseTimer();
    }

    public void startStopwatch() {
        emergingTextExercise.startTimer();
    }

    public void restartExercise() {
        emergingTextExercise.restartExercise();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        ChitaloidService chitaloidService = new ChitaloidService();
        chitaloidService.invalidateCurrentExercise();
    }
}
