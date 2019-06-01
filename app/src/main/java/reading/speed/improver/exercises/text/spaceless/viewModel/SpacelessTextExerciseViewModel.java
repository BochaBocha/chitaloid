package reading.speed.improver.exercises.text.spaceless.viewModel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import org.jetbrains.annotations.NotNull;
import reading.speed.improver.exercises.text.spaceless.SpacelessTextExercise;
import reading.speed.improver.service.ChitaloidService;

public class SpacelessTextExerciseViewModel extends AndroidViewModel {
    private MutableLiveData<String> currentText;
    private MutableLiveData<Float> currentTextSizeCoeff;
    private SpacelessTextExercise spacelessTextExercise;
    private boolean pauseDialogHidden;

    public SpacelessTextExerciseViewModel(@NotNull Application application) {
        super(application);
        ChitaloidService chitaloidService = new ChitaloidService();
        spacelessTextExercise = chitaloidService.createSpacelessTextExercise();
        currentTextSizeCoeff = spacelessTextExercise.getTextSizeCoeff();
        currentText = spacelessTextExercise.getCurrentText();
        pauseDialogHidden = true;
    }

    public MutableLiveData<Float> getCurrentTextSizeCoeff() {
        return currentTextSizeCoeff;
    }


    public MutableLiveData<String> getCurrentText() {
        return currentText;
    }


    public boolean isPauseDialogHidden() {
        return pauseDialogHidden;
    }

    public void setPauseDialogHidden(boolean value) {
        pauseDialogHidden = value;
    }

    public void changeTextSizeCoeff(final Float textSizeCoeff) {
        spacelessTextExercise.changeTextSizeCoeff(textSizeCoeff);
    }

    public void makeNewText() {
        spacelessTextExercise.makeNewText();
    }

    public void restartExercise() {
        spacelessTextExercise.restart();
        pauseDialogHidden = true;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        ChitaloidService chitaloidService = new ChitaloidService();
        chitaloidService.invalidateCurrentExercise();
    }
}
