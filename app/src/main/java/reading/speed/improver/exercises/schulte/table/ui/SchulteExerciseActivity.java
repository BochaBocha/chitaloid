package reading.speed.improver.exercises.schulte.table.ui;

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
import reading.speed.improver.exercises.schulte.table.settings.ExerciseSettingsDialogFragment;
import reading.speed.improver.exercises.schulte.table.settings.model.InitialSettingsModel;
import reading.speed.improver.exercises.schulte.table.settings.model.SelectedSettingsModel;
import reading.speed.improver.exercises.schulte.table.viewModel.SchulteExerciseViewModel;

import java.io.Serializable;


public class SchulteExerciseActivity extends AppCompatActivity implements ExerciseMenuDialogFragment.ExerciseMenuDialogListener,
        ExerciseSettingsDialogFragment.ExerciseSettingsDialogListener, SchulteTableUIManager.SchulteTableUIListener {
    private SchulteExerciseViewModel schulteExerciseViewModel;
    private View mContentView;
    private TextView timeView;
    private TextView nextNumberHint;
    private SchulteTableUIManager schulteTableUI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.schulte_exercise_activity);
        getSupportActionBar().hide();
        mContentView = findViewById(R.id.fullscreen_content);
        timeView = findViewById(R.id.stopwatch_view);
        nextNumberHint = findViewById(R.id.next_number_hint);
        schulteExerciseViewModel = ViewModelProviders.of(this).get(SchulteExerciseViewModel.class);
        final Observer<Integer> timeObserver = new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable final Integer seconds) {
                showElapsedTime(seconds);
            }
        };

        schulteExerciseViewModel.getCurrentSeconds().observe(this, timeObserver);

        final Button menuButton = (Button) findViewById(R.id.menu_button);
        menuButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onMenuButtonClicked();
            }
        });

        schulteTableUI = new SchulteTableUIManager(this);

        final Observer<Integer[][]> schulteTableObserver = new Observer<Integer[][]>() {
            @Override
            public void onChanged(@Nullable final Integer[][] schulteMatrix) {
                schulteTableUI.setSchulteMatrix(schulteMatrix);
            }
        };

        schulteExerciseViewModel.getSchulteTable().observe(this, schulteTableObserver);

        final Observer<Float> textSizeCoeffObserver = new Observer<Float>() {
            @Override
            public void onChanged(@Nullable final Float textSizeCoeff) {
                schulteTableUI.changeTextSizeCoeff(textSizeCoeff);
            }
        };

        schulteExerciseViewModel.getCurrentTextSizeCoeff().observe(this, textSizeCoeffObserver);

        final Observer<Integer> currentNumberObserver = new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable final Integer currentNumber) {
                setHintForNextNumber(currentNumber);
            }
        };

        schulteExerciseViewModel.getCurrentNumber().observe(this, currentNumberObserver);

    }

    private void setHintForNextNumber(int currentNumber) {
        nextNumberHint.setText("Next: " + Integer.toString(currentNumber));
    }

    private void onMenuButtonClicked() {
        pauseExercise();
    }

    private void pauseExercise() {
        schulteExerciseViewModel.pauseStopwatch();
        showMenuFragment();
    }

    private void showMenuFragment() {
        DialogFragment exerciseMenuDialogFragment = new ExerciseMenuDialogFragment();
        exerciseMenuDialogFragment.show(getSupportFragmentManager(), null);
    }

    private void showSettingsFragment() {
        Bundle bundle = new Bundle();
        Float currentTextSizeCoeff = schulteExerciseViewModel.getCurrentTextSizeCoeff().getValue();
        Serializable settingsModel = new InitialSettingsModel(currentTextSizeCoeff);
        bundle.putSerializable("initialSettings", settingsModel);
        DialogFragment exerciseSettingsDialogFragment = new ExerciseSettingsDialogFragment();
        exerciseSettingsDialogFragment.setArguments(bundle);
        exerciseSettingsDialogFragment.show(getSupportFragmentManager(), null);
    }

    private void showElapsedTime(final int elapsedSeconds) {
        timeView.setText(makeFormattedTimeText(elapsedSeconds));
    }

    private String makeFormattedTimeText(final int elapsedSeconds) {
        int minutes = elapsedSeconds / 60;
        int seconds = elapsedSeconds % 60;
        StringBuilder timeText = new StringBuilder();
        if (minutes < 10) {
            timeText.append("0");
        }
        timeText.append(minutes + ":");

        if (seconds < 10) {
            timeText.append("0");
        }
        timeText.append(seconds);
        return timeText.toString();
    }

    @Override
    public void onMenuContinueClick(DialogFragment dialog) {
        dialog.dismiss();
        schulteExerciseViewModel.startStopwatch();
    }

    @Override
    public void onMenuRestartClick(DialogFragment dialog) {
        dialog.dismiss();
        schulteExerciseViewModel.restartExercise();
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
        schulteExerciseViewModel.startStopwatch();
    }

    @Override
    public void onSettingsOkClick(DialogFragment dialog, SelectedSettingsModel selectedSettingsModel) {
        dialog.dismiss();
        schulteExerciseViewModel.changeTextSize(selectedSettingsModel.getSelectedTextSize());
    }

    @Override
    public void onTableCellClick(int row, int column) {
        if (schulteExerciseViewModel.isCurrentNumFound(row, column)) {
            schulteTableUI.indicateNumberIsFound(row, column);
            schulteExerciseViewModel.increaseCurrentNum();
        } else {
            schulteTableUI.indicateWrongNumberFound(row, column);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        schulteExerciseViewModel.pauseStopwatch();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!schulteExerciseViewModel.isPauseDialogHidden()) {
            schulteExerciseViewModel.setPauseDialogHidden(true);
            showMenuFragment();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        schulteExerciseViewModel.setPauseDialogHidden(false);
    }
}
