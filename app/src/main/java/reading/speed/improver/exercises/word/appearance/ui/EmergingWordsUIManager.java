package reading.speed.improver.exercises.word.appearance.ui;

import android.app.Activity;
import android.util.TypedValue;
import android.widget.ScrollView;
import android.widget.TextView;
import reading.speed.improver.R;

public class EmergingWordsUIManager {
    private float textSizeCoeff = 1;
    private String text;
    private TextView textView;
    private ScrollView scrollView;
    private Activity exerciseActivity;

    public EmergingWordsUIManager(final Activity exerciseActivity) {
        this.exerciseActivity = exerciseActivity;
        scrollView = this.exerciseActivity.findViewById(R.id.exercise_scroll_view);
        textView = scrollView.findViewById(R.id.text_view);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                exerciseActivity.getResources().getDimension(R.dimen.word_exercise_font_size) * textSizeCoeff);
    }

    public void setText(final String text) {
        this.text = text;
        textView.setText(text);
    }

    public void changeTextSizeCoeff(final float textSizeCoeff) {
        this.textSizeCoeff = textSizeCoeff;
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                exerciseActivity.getResources().getDimension(R.dimen.default_exercise_font_size) * textSizeCoeff);
    }
}
