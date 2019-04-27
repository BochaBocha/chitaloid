package reading.speed.improver.exercises.viewmodel;

import android.app.Application;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.AndroidViewModel;
import org.jetbrains.annotations.NotNull;
import reading.speed.improver.exercises.model.ExerciseModel;

public class ExerciseViewModel extends AndroidViewModel {
    private MutableLiveData<Integer> currentSeconds;
    private ExerciseModel exerciseModel;

    public ExerciseViewModel(@NotNull Application application) {
        super(application);
        exerciseModel = new ExerciseModel();
        currentSeconds = exerciseModel.getCurrentSeconds();
    }

    public MutableLiveData<Integer> getCurrentSeconds() {
        return currentSeconds;
    }

    void doAction() {
        // depending on the action, do necessary business logic calls and update the
        // userLiveData.
    }

    public void onCleared() {
        super.onCleared();
    }
}
