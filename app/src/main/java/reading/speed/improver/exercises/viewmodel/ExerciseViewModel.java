package reading.speed.improver.exercises.viewmodel;

import android.app.Application;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.AndroidViewModel;
import org.jetbrains.annotations.NotNull;
import reading.speed.improver.exercises.model.ExerciseModel;

public class ExerciseViewModel extends AndroidViewModel {

    private MutableLiveData<ExerciseModel> exerciseModelLiveData;

    public ExerciseViewModel(@NotNull Application application) {
        super(application);
    }

    public MutableLiveData<ExerciseModel> getExerciseModel() {
        if (exerciseModelLiveData == null) {
            exerciseModelLiveData = new MutableLiveData<>();
            createExerciseModel();
        }
        return exerciseModelLiveData;
    }

    private void createExerciseModel() {
        exerciseModelLiveData.setValue(new ExerciseModel());
    }

    void doAction() {
        // depending on the action, do necessary business logic calls and update the
        // userLiveData.
    }

    /*
    This method will be called when this ViewModel is no longer used and will be destroyed.

    It is useful when ViewModel observes some data and you need to clear this
    subscription to prevent a leak of this ViewModel.
  */
    public void onCleared() {
        super.onCleared();
    }
}
