package reading.speed.improver.exercises.ui;

import android.annotation.SuppressLint;
import android.view.Window;
import android.view.WindowManager;
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


public class ExerciseActivity extends AppCompatActivity {
    private ExerciseViewModel exerciseViewModel;
    private View mContentView;
    private TableLayout schulteTable;
    TextView[][] schulteTableCells;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
        schulteTableCells = new TextView[3][3];
        TextView[] firstRow = {findViewById(R.id.schulte_table_3x3_1_1),
                findViewById(R.id.schulte_table_3x3_1_2),
                findViewById(R.id.schulte_table_3x3_1_3)
        };
        schulteTableCells[0] = firstRow;
        TextView[] secondRow = {findViewById(R.id.schulte_table_3x3_2_1),
                findViewById(R.id.schulte_table_3x3_2_2),
                findViewById(R.id.schulte_table_3x3_2_3)
        };
        schulteTableCells[1] = secondRow;
        TextView[] thirdRow = {findViewById(R.id.schulte_table_3x3_3_1),
                findViewById(R.id.schulte_table_3x3_3_2),
                findViewById(R.id.schulte_table_3x3_3_3)
        };
        schulteTableCells[2] = thirdRow;
        fillSchulteTable();

    }

    private void fillSchulteTable() {
        int[][] schulteMatrix = exerciseViewModel.getSchulteTable();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                (schulteTableCells[i][j]).setText(Integer.toString(schulteMatrix[i][j]));
            }
        }
    }
}
