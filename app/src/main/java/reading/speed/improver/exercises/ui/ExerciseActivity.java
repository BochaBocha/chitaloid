package reading.speed.improver.exercises.ui;

import android.annotation.SuppressLint;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import androidx.lifecycle.ViewModelProviders;
import reading.speed.improver.R;
import reading.speed.improver.exercises.model.ExerciseModel;
import reading.speed.improver.exercises.viewmodel.ExerciseViewModel;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ExerciseActivity extends AppCompatActivity {
    private ExerciseViewModel exerciseViewModel;
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.exercise_activity);

        mContentView = findViewById(R.id.fullscreen_content);

        // Get the ViewModel.
        exerciseViewModel = ViewModelProviders.of(this).get(ExerciseViewModel.class);
        // Create the observer which updates the UI.
        final Observer<ExerciseModel> ExerciseObserver = new Observer<ExerciseModel>() {
            @Override
            public void onChanged(@Nullable final ExerciseModel exerciseModel) {
                // Update the UI, in this case, a TextView.
//                TextView textView = findViewById(R.id.dummy_text_view);
//                textView.setText(exerciseModel.getName());
            }
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        exerciseViewModel.getExerciseModel().observe(this, ExerciseObserver);

//        findViewById(R.id.dummy_button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }
}
