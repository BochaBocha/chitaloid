package reading.speed.improver.exercises.emerging.text.ui;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import reading.speed.improver.R;
import reading.speed.improver.exercises.emerging.text.settings.ExerciseSettingsDialogFragment;
import reading.speed.improver.exercises.emerging.text.settings.model.SettingsModel;
import reading.speed.improver.exercises.emerging.text.viewModel.EmergingTextExerciseViewModel;
import reading.speed.improver.exercises.menu.ExerciseMenuDialogFragment;

import java.io.Serializable;


public class EmergingTextExerciseActivity extends AppCompatActivity implements ExerciseMenuDialogFragment.ExerciseMenuDialogListener,
        ExerciseSettingsDialogFragment.ExerciseSettingsDialogListener {
    private EmergingTextExerciseViewModel exerciseViewModel;
    private View mContentView;
    private TextView currentSpeedView;
    private EmergingTextUIManager emergingTextUIManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.emerging_text_exercise_activity);
        getSupportActionBar().hide();
        mContentView = findViewById(R.id.fullscreen_content);
        currentSpeedView = findViewById(R.id.current_speed_view);
        exerciseViewModel = ViewModelProviders.of(this).get(EmergingTextExerciseViewModel.class);
        emergingTextUIManager = new EmergingTextUIManager((ScrollView) findViewById(R.id.exercise_scroll_view));
        final Button menuButton = (Button) findViewById(R.id.menu_button);
        menuButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onMenuButtonClicked();
            }
        });


        final Observer<Float> speedObserver = new Observer<Float>() {
            @Override
            public void onChanged(@Nullable final Float speed) {
                displayCurrentSpeed(speed);
            }
        };
        exerciseViewModel.getCurrentSpeed().observe(this, speedObserver);

        final Observer<Boolean> autoScrollOptionObserver = new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable final Boolean autoScrollOption) {
                if (autoScrollOption) {
                    emergingTextUIManager.enableAutoScroll();
                } else {
                    emergingTextUIManager.disableAutoScroll();
                }
            }
        };
        exerciseViewModel.getAutoScrollOption().observe(this, autoScrollOptionObserver);

        final Observer<String> currentTextObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String text) {
                emergingTextUIManager.setText(text);
            }
        };
        exerciseViewModel.getCurrentText().observe(this, currentTextObserver);

    }

    private void displayCurrentSpeed(Float speed) {
        currentSpeedView.setText("Скорость: " + Float.toString(speed));
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
        Serializable settingsModel = new SettingsModel(exerciseViewModel.getCurrentTextSize().getValue(),
                exerciseViewModel.getCurrentSpeed().getValue(),
                exerciseViewModel.getAutoScrollOption().getValue());
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
        exerciseViewModel.startStopwatch();
    }

    @Override
    protected void onStop() {
        super.onStop();
        pauseExercise();
    }

    @Override
    public void onSettingsOkClick(DialogFragment dialog, SettingsModel settingsModel) {
        dialog.dismiss();
        exerciseViewModel.changeTextSize(settingsModel.getTextSize());
        exerciseViewModel.setAutoScrollOption(settingsModel.isAutoScrollEnabled());
    }
}
