package reading.speed.improver.exercises.text.appearence.emerging.ui;

import android.app.Activity;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import reading.speed.improver.R;

public class EmergingTextUIManager {
    private float textSizeCoeff = 1;
    private String text;
    private TextView textView;
    private ScrollView scrollView;
    private boolean autoScroll;
    private Activity exerciseActivity;

    public EmergingTextUIManager(final Activity exerciseActivity) {
        this.exerciseActivity = exerciseActivity;
        scrollView = this.exerciseActivity.findViewById(R.id.exercise_scroll_view);
        textView = scrollView.findViewById(R.id.emerging_text_view);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                exerciseActivity.getResources().getDimension(R.dimen.default_exercise_font_size) * textSizeCoeff);
        enableAutoScroll();
    }

    public void setText(final String text) {
        this.text = text;
        textView.setText(text);
        if (autoScroll) {
            scrollView.fullScroll(View.FOCUS_DOWN);
        }
    }

    public void enableAutoScroll() {
        this.autoScroll = true;
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    public void disableAutoScroll() {
        this.autoScroll = false;
        scrollView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
    }

    public void changeTextSizeCoeff(final float textSizeCoeff) {
        this.textSizeCoeff = textSizeCoeff;
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                exerciseActivity.getResources().getDimension(R.dimen.default_exercise_font_size) * textSizeCoeff);
    }
}
