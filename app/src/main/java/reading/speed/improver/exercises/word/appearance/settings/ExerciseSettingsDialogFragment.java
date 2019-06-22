package reading.speed.improver.exercises.word.appearance.settings;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import reading.speed.improver.R;
import reading.speed.improver.exercises.word.appearance.settings.model.SettingsModel;


// TODO: fix crash when onStop() occurs
public class ExerciseSettingsDialogFragment extends DialogFragment {
    SettingsModel settingsModel;
    private SeekBar fontSizeSeekBar;
    private SeekBar speedSeekBar;
    private SeekBar amountOfLettersSeekBar;
    private TextView fontSizeSelectedTextView;
    private TextView speedSelectedTextView;
    private TextView amountOfLetterTextView;
    private View view;

    public interface ExerciseSettingsDialogListener {
        void onSettingsOkClick(DialogFragment dialog, SettingsModel settingsModel);
    }

    ExerciseSettingsDialogListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (ExerciseSettingsDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    //TODO: speed selection
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            settingsModel = (SettingsModel) getArguments().getSerializable("initialSettings");
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        view = inflater.inflate(R.layout.emerging_words_exercise_settings, null);

        builder.setView(view);

        fontSizeSelectedTextView = view.findViewById(R.id.font_size_selected);

        fontSizeSeekBar = view.findViewById(R.id.seekBar_font_size);
        initFontSizeSeekBar();

        speedSeekBar = view.findViewById(R.id.seekBar_speed);

        speedSelectedTextView = view.findViewById(R.id.speed_selected);
        initSpeedSeekBar();

        amountOfLettersSeekBar = view.findViewById(R.id.amount_of_letters_seekBar);

        amountOfLetterTextView = view.findViewById(R.id.amount_of_letters_selected);
        initAmountOfLettersSeekBar();

        Button settingsButton = view.findViewById(R.id.button_ok);

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onSettingsOkClick(ExerciseSettingsDialogFragment.this,
                        new SettingsModel(fontSizeSeekBar.getProgress() * 0.1f + 1,
                                speedSeekBar.getProgress() * 10 + 60,
                                amountOfLettersSeekBar.getProgress() + 3));
            }
        });

        return builder.create();
    }

    private void initFontSizeSeekBar() {
        fontSizeSeekBar.setMax(10);
        fontSizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                fontSizeSelectedTextView.setText("Размер шрифта: " + String.valueOf((progress) * 10 + 100) + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                fontSizeSelectedTextView.setText("Размер шрифта: " + String.valueOf(seekBar.getProgress() * 10 + 100) + "%");
            }
        });

        fontSizeSeekBar.setProgress(Math.round((settingsModel.getTextSizeCoeff() - 1) * 10));
    }

    private void initSpeedSeekBar() {
        speedSeekBar.setMax(45);
        speedSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                speedSelectedTextView.setText("Скорость: " + String.valueOf(((progress) * 10) + 60) + " слов в минуту");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                speedSelectedTextView.setText("Скорость: " +
                        String.valueOf(((seekBar.getProgress()) * 10) + 60) +
                        " слов в минуту");
            }
        });

        speedSeekBar.setProgress((settingsModel.getSpeed() - 60) / 10);
    }

    private void initAmountOfLettersSeekBar() {
        amountOfLettersSeekBar.setMax(5);
        amountOfLettersSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                amountOfLetterTextView.setText("Букв в слове: " + String.valueOf(progress + 3));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                amountOfLetterTextView.setText("Букв в слове: " +
                        String.valueOf(seekBar.getProgress() + 3));
            }
        });
        amountOfLettersSeekBar.setProgress((settingsModel.getAmountOfLetters() - 3));
    }
}
