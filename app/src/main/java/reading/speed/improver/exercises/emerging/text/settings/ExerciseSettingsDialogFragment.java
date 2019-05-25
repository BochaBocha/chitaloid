package reading.speed.improver.exercises.emerging.text.settings;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import reading.speed.improver.R;
import reading.speed.improver.exercises.emerging.text.settings.model.SettingsModel;

public class ExerciseSettingsDialogFragment extends DialogFragment {
    SettingsModel settingsModel;

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
        View view = inflater.inflate(R.layout.emerging_text_exercise_settings, null);
        builder.setView(view);
        final TextView fontSizeSelectedTextView = view.findViewById(R.id.font_size_selected);
        final SeekBar fontSizeSeekBar = view.findViewById(R.id.seekBar_font_size);
        final CheckBox autoScrollCheckBox = view.findViewById(R.id.autoScroll_checkBox);
        autoScrollCheckBox.setChecked(settingsModel.isAutoScrollEnabled());
        fontSizeSeekBar.setMax(5);
        fontSizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                fontSizeSelectedTextView.setText(String.valueOf((progress) * 10 + 100) + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                fontSizeSelectedTextView.setText(String.valueOf(seekBar.getProgress() * 10 + 100) + "%");
            }
        });

        fontSizeSeekBar.setProgress((int) (settingsModel.getTextSize() - 100) / 10);
        Button settingsButton = view.findViewById(R.id.button_ok);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onSettingsOkClick(ExerciseSettingsDialogFragment.this,
                        new SettingsModel(fontSizeSeekBar.getProgress() * 10 + 100, 1,
                                autoScrollCheckBox.isChecked()));
            }
        });
        return builder.create();
    }
}
