package reading.speed.improver.exercises.emerging.text.viewModel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import org.jetbrains.annotations.NotNull;
import reading.speed.improver.exercises.emerging.text.EmergingTextExercise;
import reading.speed.improver.repository.ChitaloidRepository;

public class EmergingTextExerciseViewModel extends AndroidViewModel {
    private MutableLiveData<Float> currentSpeed;
    private MutableLiveData<String> currentText;
    private MutableLiveData<Float> currentTextSizeCoeff;
    private MutableLiveData<Boolean> autoScrollOption;
    private EmergingTextExercise emergingTextExercise;

    public EmergingTextExerciseViewModel(@NotNull Application application) {
        super(application);

        emergingTextExercise = ChitaloidRepository.getInstance().getEmergingTextExerciseModel();
        emergingTextExercise.startStopwatch();
        currentTextSizeCoeff = emergingTextExercise.getTextSizeCoeff();
        currentSpeed = emergingTextExercise.getCurrentSpeed();
        currentText = emergingTextExercise.getCurrentText();
        autoScrollOption = emergingTextExercise.getAutoScrollOption();
    }

    public MutableLiveData<Float> getCurrentTextSizeCoeff() {
        return currentTextSizeCoeff;
    }

    public MutableLiveData<Float> getCurrentSpeed() {
        return currentSpeed;
    }

    public MutableLiveData<String> getCurrentText() {
        return currentText;
    }

    public MutableLiveData<Boolean> getAutoScrollOption() {
        return autoScrollOption;
    }

    public void changeTextSizeCoeff(final Float textSizeCoeff) {
        emergingTextExercise.changeTextSizeCoeff(textSizeCoeff);
    }

    public void setAutoScrollOption(final Boolean autoScrollOption) {
        emergingTextExercise.setAutoScrollOption(autoScrollOption);
    }

    public void pauseStopwatch() {
        emergingTextExercise.pauseStopwatch();
    }

    public void startStopwatch() {
        emergingTextExercise.startStopwatch();
    }

    public void restartExercise() {
        emergingTextExercise.restartExercise();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        ChitaloidRepository.getInstance().invalidateCurrentExerciseModel();
    }
}
