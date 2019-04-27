package reading.speed.improver.exercises.ui;

import android.annotation.SuppressLint;
import android.widget.TableLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import androidx.lifecycle.ViewModelProviders;
import reading.speed.improver.R;
import reading.speed.improver.exercises.viewmodel.ExerciseViewModel;

import java.util.ArrayList;


public class ExerciseActivity extends AppCompatActivity {
    private ExerciseViewModel exerciseViewModel;
    private View mContentView;
    private TableLayout schulteTable;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_activity);
        mContentView = findViewById(R.id.fullscreen_content);

        exerciseViewModel = ViewModelProviders.of(this).get(ExerciseViewModel.class);
        // Create the observer which updates the UI.
        final Observer<Integer> timeObserver = new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable final Integer seconds) {
                // Update the UI, in this case, a TextView.
                TextView textView = findViewById(R.id.stopwatch_view);
                textView.setText(Integer.toString(seconds));
            }
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        exerciseViewModel.getCurrentSeconds().observe(this, timeObserver);

        schulteTable = findViewById(R.id.schulte_table_3x3);
    }
}
