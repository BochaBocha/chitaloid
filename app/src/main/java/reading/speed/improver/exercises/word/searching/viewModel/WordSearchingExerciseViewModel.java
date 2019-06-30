package reading.speed.improver.exercises.word.searching.viewModel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import org.jetbrains.annotations.NotNull;
import reading.speed.improver.exercises.word.searching.WordSearchingExercise;
import reading.speed.improver.service.ChitaloidService;

import java.util.ArrayList;

public class WordSearchingExerciseViewModel extends AndroidViewModel {
    private MutableLiveData<Integer> currentSpeed;
    private MutableLiveData<String> wantedWord;

    public MutableLiveData<ArrayList<String>> getWords() {
        return words;
    }

    public void setWords(MutableLiveData<ArrayList<String>> words) {
        this.words = words;
    }

    private MutableLiveData<ArrayList<String>> words;
    private MutableLiveData<Float> currentTextSizeCoeff;
    private WordSearchingExercise wordSearchingExercise;
    private boolean pauseDialogHidden;
        
    public WordSearchingExerciseViewModel(@NotNull Application application) {
        super(application);
        ChitaloidService chitaloidService = new ChitaloidService();
        wordSearchingExercise = chitaloidService.createWordSearchingExercise();
        wordSearchingExercise.startTimer();
        currentTextSizeCoeff = wordSearchingExercise.getTextSizeCoeff();
        currentSpeed = wordSearchingExercise.getCurrentSpeed();
        wantedWord = wordSearchingExercise.getWantedWord();
        words = wordSearchingExercise.getWords();
        pauseDialogHidden = true;
    }

    public MutableLiveData<Float> getCurrentTextSizeCoeff() {
        return currentTextSizeCoeff;
    }

    public MutableLiveData<Integer> getCurrentSpeed() {
        return currentSpeed;
    }

    public MutableLiveData<String> getWantedWord() {
        return wantedWord;
    }


    public boolean isPauseDialogHidden() {
        return pauseDialogHidden;
    }

    public void setPauseDialogHidden(boolean value) {
        pauseDialogHidden = value;
    }

    public void changeTextSizeCoeff(final Float textSizeCoeff) {
        wordSearchingExercise.changeTextSizeCoeff(textSizeCoeff);
    }

    public void setSpeed(final int speed){
        wordSearchingExercise.changeSpeed(speed);
    }

    public void pauseStopwatch() {
        wordSearchingExercise.pauseTimer();
    }

    public void startStopwatch() {
        wordSearchingExercise.startTimer();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        ChitaloidService chitaloidService = new ChitaloidService();
        chitaloidService.invalidateCurrentExercise();
    }
}
