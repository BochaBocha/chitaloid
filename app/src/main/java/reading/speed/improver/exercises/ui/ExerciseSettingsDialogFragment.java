package reading.speed.improver.exercises.ui;

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

public class ExerciseSettingsDialogFragment extends DialogFragment {

    public interface ExerciseSettingsDialogListener {
        void onSettingsOkClick(DialogFragment dialog);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_exercise_settings, null);
        builder.setView(view);
        Button settingsButton = (Button) view.findViewById(R.id.button_ok);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onSettingsOkClick(ExerciseSettingsDialogFragment.this);
            }
        });
        final TextView fontSizeSelectedTextView = (TextView) view.findViewById(R.id.font_size_selected);
        SeekBar fontSizeSeekBar = (SeekBar) view.findViewById(R.id.seekBar_font_size);
        fontSizeSeekBar.setMax(10);
        fontSizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                fontSizeSelectedTextView.setText(String.valueOf(seekBar.getProgress()));
            }
        });
        return builder.create();
    }

}
