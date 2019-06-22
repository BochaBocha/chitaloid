package reading.speed.improver.user.pupil.management;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import androidx.fragment.app.DialogFragment;
import reading.speed.improver.R;
import reading.speed.improver.user.pupil.Pupil;

public class PupilOptionsDialog extends DialogFragment {

    public interface PupilOptionsDialogListener {
        void onShowStatisticClick(DialogFragment dialog, Pupil pupil);

        void onDeleteStatisticClick(DialogFragment dialog, Pupil pupil);

        void onDeletePupilClick(DialogFragment dialog, Pupil pupil);
    }

    PupilOptionsDialogListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (PupilOptionsDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement PupilOptionsDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.pupil_options_dialog, null);
        builder.setView(view);
        if (getArguments() == null) throw new AssertionError();
        final Pupil pupil = (Pupil) getArguments().getSerializable("pupil");
        Button showStatisticButton = view.findViewById(R.id.show_statistic_btn);
        showStatisticButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onShowStatisticClick(PupilOptionsDialog.this, pupil);
            }
        });
        Button deleteStatisticButton = view.findViewById(R.id.delete_statistic_btn);
        deleteStatisticButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onDeleteStatisticClick(PupilOptionsDialog.this, pupil);
            }
        });

        Button deletePupilButton = view.findViewById(R.id.delete_pupil_btn);
        deletePupilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onDeletePupilClick(PupilOptionsDialog.this, pupil);
            }
        });
        return builder.create();
    }
}
