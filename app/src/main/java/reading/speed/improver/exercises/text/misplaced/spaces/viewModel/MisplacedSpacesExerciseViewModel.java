package reading.speed.improver.exercises.text.misplaced.spaces.viewModel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import org.jetbrains.annotations.NotNull;
import reading.speed.improver.exercises.text.misplaced.spaces.MisplacedSpacesExercise;
import reading.speed.improver.service.ChitaloidService;

public class MisplacedSpacesExerciseViewModel extends AndroidViewModel {
    private MutableLiveData<String> currentText;
    private MutableLiveData<Float> currentTextSizeCoeff;
    private MisplacedSpacesExercise misplacedSpacesExercise;
    private boolean pauseDialogHidden;

    public MisplacedSpacesExerciseViewModel(@NotNull Application application) {
        super(application);
        ChitaloidService chitaloidService = new ChitaloidService();
        misplacedSpacesExercise = chitaloidService.createMisplacedSpacesExercise();
        currentTextSizeCoeff = misplacedSpacesExercise.getTextSizeCoeff();
        currentText = misplacedSpacesExercise.getCurrentText();
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
        misplacedSpacesExercise.changeTextSizeCoeff(textSizeCoeff);
    }

    public void makeNewText() {
        misplacedSpacesExercise.makeNewText();
    }

    public void restartExercise() {
        misplacedSpacesExercise.restart();
        pauseDialogHidden = true;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        ChitaloidService chitaloidService = new ChitaloidService();
        chitaloidService.invalidateCurrentExercise();
    }
}
