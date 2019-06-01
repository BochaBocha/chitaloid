package reading.speed.improver.exercises.text.spaceless.ui;

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
import reading.speed.improver.exercises.menu.ExerciseMenuDialogFragment;
import reading.speed.improver.exercises.text.TextUIManager;
import reading.speed.improver.exercises.text.settings.ExerciseSettingsDialogFragment;
import reading.speed.improver.exercises.text.settings.model.SettingsModel;
import reading.speed.improver.exercises.text.spaceless.viewModel.SpacelessTextExerciseViewModel;

import java.io.Serializable;


public class SpacelessTextExerciseActivity extends AppCompatActivity implements ExerciseMenuDialogFragment.ExerciseMenuDialogListener,
        ExerciseSettingsDialogFragment.ExerciseSettingsDialogListener {
    private SpacelessTextExerciseViewModel exerciseViewModel;
    private View mContentView;
    private TextView currentSpeedView;
    private TextUIManager textUIManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.text_exercise_activity);
        getSupportActionBar().hide();
        mContentView = findViewById(R.id.fullscreen_content);
        exerciseViewModel = ViewModelProviders.of(this).get(SpacelessTextExerciseViewModel.class);
        textUIManager = new TextUIManager(this);
        final Button menuButton = findViewById(R.id.menu_button);
        menuButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onMenuButtonClicked();
            }
        });

        final Observer<Float> textSizeCoeffObserver = new Observer<Float>() {
            @Override
            public void onChanged(@Nullable final Float textSizeCoeff) {
                textUIManager.changeTextSizeCoeff(textSizeCoeff);
            }
        };
        exerciseViewModel.getCurrentTextSizeCoeff().observe(this, textSizeCoeffObserver);

        final Observer<String> currentTextObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String text) {
                textUIManager.setText(text);
            }
        };
        exerciseViewModel.getCurrentText().observe(this, currentTextObserver);

        Button getNextText = findViewById(R.id.get_next_text);
        getNextText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exerciseViewModel.makeNewText();
            }
        });

    }

    private void onMenuButtonClicked() {
        pauseExercise();
    }

    private void pauseExercise() {
        showMenuFragment();
    }

    private void showMenuFragment() {
        DialogFragment exerciseMenuDialogFragment = new ExerciseMenuDialogFragment();
        exerciseMenuDialogFragment.show(getSupportFragmentManager(), null);
    }

    private void showSettingsFragment() {
        Bundle bundle = new Bundle();
        Serializable settingsModel = new SettingsModel(exerciseViewModel.getCurrentTextSizeCoeff().getValue());
        bundle.putSerializable("initialSettings", settingsModel);
        DialogFragment exerciseSettingsDialogFragment = new ExerciseSettingsDialogFragment();
        exerciseSettingsDialogFragment.setArguments(bundle);
        exerciseSettingsDialogFragment.show(getSupportFragmentManager(), null);
    }

    @Override
    public void onMenuContinueClick(DialogFragment dialog) {
        dialog.dismiss();
    }

    @Override
    public void onMenuRestartClick(DialogFragment dialog) {
        dialog.dismiss();
        exerciseViewModel.restartExercise();
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
    }

    @Override
    protected void onStop() {
        super.onStop();
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
    }
}
