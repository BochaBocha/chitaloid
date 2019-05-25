package reading.speed.improver.exercises.emerging.text.ui;

import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import reading.speed.improver.R;

public class EmergingTextUIManager {
    private float textSize = 100;
    private String text;
    private TextView textView;
    private ScrollView scrollView;
    private boolean autoScroll;

    public EmergingTextUIManager(final ScrollView scrollView) {
        this.scrollView = scrollView;
        this.textView = scrollView.findViewById(R.id.emerging_text_view);
        enableAutoScroll();
    }

    public void setText(final String text) {
        this.text = text;
        textView.setText(text);
        if (autoScroll) {
            scrollView.fullScroll(View.FOCUS_DOWN);
        }
    }

    public void enableAutoScroll(){
        this.autoScroll = true;
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
    }

    public void disableAutoScroll(){
        this.autoScroll = false;
        scrollView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    public void changeTextSize(final float textSize) {
        this.textSize = textSize;
        textView.setTextSize(textSize);
    }
}
