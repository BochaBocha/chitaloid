package reading.speed.improver.exercises.text.appearence.fading.viewModel;

import android.app.Application;
import android.text.SpannableString;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import org.jetbrains.annotations.NotNull;
import reading.speed.improver.exercises.text.appearence.fading.FadingTextExercise;
import reading.speed.improver.service.ChitaloidService;

public class FadingTextExerciseViewModel extends AndroidViewModel {
    private MutableLiveData<Integer> currentSpeed;
    private MutableLiveData<SpannableString> currentText;
    private MutableLiveData<Float> currentTextSizeCoeff;
    private FadingTextExercise fadingTextExercise;
    private boolean pauseDialogHidden;

    public FadingTextExerciseViewModel(@NotNull Application application) {
        super(application);
        ChitaloidService chitaloidService = new ChitaloidService();
        fadingTextExercise = chitaloidService.createFadingTextExerciseModel();
        fadingTextExercise.startTimer();
        currentTextSizeCoeff = fadingTextExercise.getTextSizeCoeff();
        currentSpeed = fadingTextExercise.getCurrentSpeed();
        currentText = fadingTextExercise.getCurrentText();
        pauseDialogHidden = true;
    }

    public boolean isPauseDialogHidden() {
        return pauseDialogHidden;
    }

    public void setPauseDialogHidden(boolean value) {
        pauseDialogHidden = value;
    }

    public MutableLiveData<Float> getCurrentTextSizeCoeff() {
        return currentTextSizeCoeff;
    }

    public MutableLiveData<Integer> getCurrentSpeed() {
        return currentSpeed;
    }

    public MutableLiveData<SpannableString> getCurrentText() {
        return currentText;
    }

    public void changeTextSizeCoeff(final Float textSizeCoeff) {
        fadingTextExercise.changeTextSizeCoeff(textSizeCoeff);
    }

    public void setSpeed(final int speed){
        fadingTextExercise.setSpeed(speed);
    }

    public void pauseStopwatch() {
        fadingTextExercise.pauseTimer();
    }

    public void startStopwatch() {
        fadingTextExercise.startTimer();
    }

    public void restartExercise() {
        fadingTextExercise.restartExercise();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        ChitaloidService chitaloidService = new ChitaloidService();
        chitaloidService.invalidateCurrentExercise();
    }
}
