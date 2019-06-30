package reading.speed.improver.exercises.word.appearance.ui;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import reading.speed.improver.R;
import reading.speed.improver.exercises.word.appearance.menu.ExerciseMenuDialogFragment;
import reading.speed.improver.exercises.word.appearance.settings.ExerciseSettingsDialogFragment;
import reading.speed.improver.exercises.word.appearance.settings.model.SettingsModel;
import reading.speed.improver.exercises.word.appearance.viewModel.EmergingWordsExerciseViewModel;

import java.io.Serializable;


public class EmergingWordsExerciseActivity extends AppCompatActivity implements ExerciseMenuDialogFragment.ExerciseMenuDialogListener,
        ExerciseSettingsDialogFragment.ExerciseSettingsDialogListener {
    private EmergingWordsExerciseViewModel exerciseViewModel;
    private View mContentView;
    private TextView currentSpeedView;
    private EmergingWordsUIManager emergingWordsUIManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.emerging_words_exercise_activity);
        getSupportActionBar().hide();
        mContentView = findViewById(R.id.fullscreen_content);
        currentSpeedView = findViewById(R.id.current_speed_view);
        exerciseViewModel = ViewModelProviders.of(this).get(EmergingWordsExerciseViewModel.class);
        emergingWordsUIManager = new EmergingWordsUIManager(this);
        final Button menuButton = findViewById(R.id.menu_button);
        menuButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onMenuButtonClicked();
            }
        });

        final Observer<Integer> speedObserver = new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable final Integer speed) {
                displayCurrentSpeed(speed);
            }
        };
        exerciseViewModel.getCurrentSpeed().observe(this, speedObserver);

        final Observer<Float> textSizeCoeffObserver = new Observer<Float>() {
            @Override
            public void onChanged(@Nullable final Float textSizeCoeff) {
                emergingWordsUIManager.changeTextSizeCoeff(textSizeCoeff);
            }
        };
        exerciseViewModel.getCurrentTextSizeCoeff().observe(this, textSizeCoeffObserver);

        final Observer<String> currentTextObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String text) {
                emergingWordsUIManager.setText(text);
            }
        };
        exerciseViewModel.getCurrentText().observe(this, currentTextObserver);

    }

    private void displayCurrentSpeed(Integer speed) {
        currentSpeedView.setText("Скорость: " + speed + " слов в минуту");
    }

    private void onMenuButtonClicked() {
        pauseExercise();
    }

    private void pauseExercise() {
        exerciseViewModel.pauseStopwatch();
        showMenuFragment();
    }

    private void showMenuFragment() {
        DialogFragment exerciseMenuDialogFragment = new ExerciseMenuDialogFragment();
        exerciseMenuDialogFragment.show(getSupportFragmentManager(), null);
    }

    private void showSettingsFragment() {
        Bundle bundle = new Bundle();
        Serializable settingsModel = new SettingsModel(exerciseViewModel.getCurrentTextSizeCoeff().getValue(),
                exerciseViewModel.getCurrentSpeed().getValue(),
                exerciseViewModel.getAmountOfLetters().getValue());
        bundle.putSerializable("initialSettings", settingsModel);
        DialogFragment exerciseSettingsDialogFragment = new ExerciseSettingsDialogFragment();
        exerciseSettingsDialogFragment.setArguments(bundle);
        exerciseSettingsDialogFragment.show(getSupportFragmentManager(), null);
    }

    @Override
    public void onMenuContinueClick(DialogFragment dialog) {
        dialog.dismiss();
        exerciseViewModel.startStopwatch();
    }

    @Override
    public void onMenuSettingsClick(DialogFragment dialog) {
        showSettingsFragment();
    }

    @Override
    public void onMenuExitClick(DialogFragment dialog) {
        dialog.dismiss();
        finish();
    }

    @Override
    public void onMenuDestroy(DialogFragment dialog) {
        exerciseViewModel.startStopwatch();
    }

    @Override
    protected void onStop() {
        super.onStop();
        exerciseViewModel.pauseStopwatch();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!exerciseViewModel.isPauseDialogHidden()) {
            exerciseViewModel.setPauseDialogHidden(true);
            showMenuFragment();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        exerciseViewModel.setPauseDialogHidden(false);
    }

    @Override
    public void onSettingsOkClick(DialogFragment dialog, SettingsModel settingsModel) {
        dialog.dismiss();
        exerciseViewModel.changeTextSizeCoeff(settingsModel.getTextSizeCoeff());
        exerciseViewModel.setAmountOfLetters(settingsModel.getAmountOfLetters());
        exerciseViewModel.setSpeed(settingsModel.getSpeed());
    }
}
