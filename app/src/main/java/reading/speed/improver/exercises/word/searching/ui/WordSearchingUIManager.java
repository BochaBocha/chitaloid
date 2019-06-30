package reading.speed.improver.exercises.word.searching.ui;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import reading.speed.improver.R;

import java.util.ArrayList;
import java.util.Random;

public class WordSearchingUIManager {
    private float textSizeCoeff = 1;
    private ArrayList<String> words;
    private String wantedWord;
    private TextView wantedWordTextView;
    private TextView wordsTextView;
    private ScrollView scrollView;
    private Activity exerciseActivity;

    public WordSearchingUIManager(final Activity exerciseActivity) {
        this.exerciseActivity = exerciseActivity;
        scrollView = this.exerciseActivity.findViewById(R.id.exercise_scroll_view);
        wantedWordTextView = scrollView.findViewById(R.id.wanted_word_textView);
        wantedWordTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                exerciseActivity.getResources().getDimension(R.dimen.word_exercise_font_size) * textSizeCoeff);
        wordsTextView = scrollView.findViewById(R.id.words_textView);
        wordsTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                exerciseActivity.getResources().getDimension(R.dimen.word_exercise_font_size) * textSizeCoeff);
        words = new ArrayList<>();
        wantedWord = "";

    }

    public void setWords(final ArrayList<String> words) {
        this.words = words;
        wordsTextView.setText(constructClickableWords(words));
    }

    private SpannableString constructClickableWords(final ArrayList<String> words) {
//        ClickableSpan wantedWordSpan = new ClickableSpan() {
//            @Override
//            public void onClick(View textView) {
//                System.out.println("found!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1");
//                changeBackgroundForMillis(Color.GREEN);
//            }
//        };
//        ClickableSpan wrongWordSpan = new ClickableSpan() {
//            @Override
//            public void onClick(View textView) {
//                System.out.println("WROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOONNNNNNNNNNNNNGGGGGGGGGGGGGGGGGGG");
//                changeBackgroundForMillis(Color.RED);
//            }
//        };
//        SpannableStringBuilder spannableStringBuilder = new SpannableString("");
//        int wantedWordPosition = new Random().nextInt(words.size() - 1);
//        int symbolCount = 0;
//        for (int i = 0; i < words.size(); i++) {
//            if (i == wantedWordPosition) {
//                spannableString =  new SpannableString(wantedWord);
//                spannableStringBuilder.setSpan(wantedWordSpan, symbolCount, wantedWord.length() + symbolCount,
//                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//                symbolCount += wantedWord.length();
//                spannableStringBuilder.append("  ");
//                symbolCount += 2;
//            }
//            spannableStringBuilder.append(words.get(i));
//            int wordLength = words.get(i).length();
//            spannableStringBuilder.setSpan(wrongWordSpan, symbolCount, wordLength + symbolCount,
//                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//            symbolCount += wordLength;
//            spannableStringBuilder.append("  ");
//            symbolCount += 2;
//        }
        return new SpannableString("");
    }

    private void changeBackgroundForMillis(final int backgroundColor) {
        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LinearLayout linearLayout = scrollView.findViewById(R.id.exercise_background);
                linearLayout.setBackgroundColor(backgroundColor);
            }
        }, 150);
        LinearLayout linearLayout = scrollView.findViewById(R.id.exercise_background);
        linearLayout.setBackgroundColor(Color.WHITE);
    }

    public void setWantedWord(final String wantedWord) {
        this.wantedWord = wantedWord;
        wantedWordTextView.setText(wantedWord);
    }

    public void changeTextSizeCoeff(final float textSizeCoeff) {
        this.textSizeCoeff = textSizeCoeff;
        wantedWordTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                exerciseActivity.getResources().getDimension(R.dimen.word_exercise_font_size) * textSizeCoeff);
        wordsTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                exerciseActivity.getResources().getDimension(R.dimen.word_exercise_font_size) * textSizeCoeff);

    }
}
