package reading.speed.improver.exercises.word.appearance.menu;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import reading.speed.improver.R;

public class ExerciseMenuDialogFragment extends DialogFragment {

    public interface ExerciseMenuDialogListener {
        void onMenuContinueClick(DialogFragment dialog);

        void onMenuSettingsClick(DialogFragment dialog);

        void onMenuExitClick(DialogFragment dialog);

        void onMenuDestroy(DialogFragment dialog);
    }

    ExerciseMenuDialogListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (ExerciseMenuDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_exercise_menu_small, null);
        builder.setView(view);
        Button continueButton = view.findViewById(R.id.button_continue);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onMenuContinueClick(ExerciseMenuDialogFragment.this);
            }
        });
        Button settingsButton = view.findViewById(R.id.button_settings);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onMenuSettingsClick(ExerciseMenuDialogFragment.this);
            }
        });
        Button exitButton =  view.findViewById(R.id.button_exit);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onMenuExitClick(ExerciseMenuDialogFragment.this);
            }
        });
        return builder.create();
    }

    @Override
    public void onDestroy() {
        mListener.onMenuDestroy(ExerciseMenuDialogFragment.this);
        super.onDestroy();

    }
}
