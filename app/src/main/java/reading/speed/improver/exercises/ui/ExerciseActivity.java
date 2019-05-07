package reading.speed.improver.exercises.ui;

import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import androidx.lifecycle.ViewModelProviders;
import reading.speed.improver.R;
import reading.speed.improver.exercises.viewmodel.ExerciseViewModel;


public class ExerciseActivity extends AppCompatActivity implements ExerciseMenuDialogFragment.ExerciseMenuDialogListener,
        ExerciseSettingsDialogFragment.ExerciseSettingsDialogListener {
    private ExerciseViewModel exerciseViewModel;
    private View mContentView;
    private SchulteTableUIManager schulteTableUI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.exercise_activity);
        mContentView = findViewById(R.id.fullscreen_content);

        exerciseViewModel = ViewModelProviders.of(this).get(ExerciseViewModel.class);
        final Observer<Integer> timeObserver = new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable final Integer seconds) {
                showElapsedTime(seconds);
            }
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        exerciseViewModel.getCurrentSeconds().observe(this, timeObserver);

        final Button menuButton = (Button) findViewById(R.id.menu_button);
        menuButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onMenuButtonClicked();
            }
        });

        schulteTableUI = new SchulteTableUIManager((TableLayout) findViewById(R.id.schulte_table_3x3));

        final Observer<Integer[][]> schulteTableObserver = new Observer<Integer[][]>() {
            @Override
            public void onChanged(@Nullable final Integer[][] schulteMatrix) {
                schulteTableUI.fillSchulteTable(schulteMatrix);
            }
        };
        exerciseViewModel.getSchulteTable().observe(this, schulteTableObserver);
        schulteTableUI.setOnClickListenersForTableElements();

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
        DialogFragment exerciseSettingsDialogFragment = new ExerciseSettingsDialogFragment();
        exerciseSettingsDialogFragment.show(getSupportFragmentManager(), null);
    }

    private void showElapsedTime(final int elapsedSeconds) {
        TextView timeView = findViewById(R.id.stopwatch_view);
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
    public void onSettingsOkClick(DialogFragment dialog) {
        dialog.dismiss();
    }
}
