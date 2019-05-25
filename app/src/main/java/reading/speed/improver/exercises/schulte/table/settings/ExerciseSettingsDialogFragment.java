package reading.speed.improver.exercises.schulte.table.settings;

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
import reading.speed.improver.exercises.schulte.table.settings.model.InitialSettingsModel;
import reading.speed.improver.exercises.schulte.table.settings.model.SelectedSettingsModel;

public class ExerciseSettingsDialogFragment extends DialogFragment {
    InitialSettingsModel initialSettingsModel;

    public interface ExerciseSettingsDialogListener {
        void onSettingsOkClick(DialogFragment dialog, SelectedSettingsModel selectedSettingsModel);
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

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            initialSettingsModel = (InitialSettingsModel) getArguments().getSerializable("initialSettings");
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.schulte_table_exercise_settings, null);
        builder.setView(view);
        final TextView fontSizeSelectedTextView = (TextView) view.findViewById(R.id.font_size_selected);
        final SeekBar fontSizeSeekBar = (SeekBar) view.findViewById(R.id.seekBar_font_size);
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

        fontSizeSeekBar.setProgress((int) (initialSettingsModel.getInitialTextSize() - 100) / 10);
        Button settingsButton =  view.findViewById(R.id.button_ok);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onSettingsOkClick(ExerciseSettingsDialogFragment.this,
                        new SelectedSettingsModel(fontSizeSeekBar.getProgress() * 10 + 100));
            }
        });
        return builder.create();
    }
}
