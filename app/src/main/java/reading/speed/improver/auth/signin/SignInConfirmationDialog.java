package reading.speed.improver.auth.signin;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import org.jetbrains.annotations.NotNull;
import reading.speed.improver.R;
import reading.speed.improver.user.pupil.Pupil;

public class SignInConfirmationDialog extends DialogFragment {

    public interface SignInConfirmationDialogListener {
        void onOkClick(DialogFragment dialog, Pupil pupil);

        void onCancelClick(DialogFragment dialog);
    }

    SignInConfirmationDialogListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (SignInConfirmationDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement SignInConfirmationDialogListener");
        }
    }

    @NotNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.sign_in_confirmation_dialog, null);
        builder.setView(view);
        if (getArguments() == null) throw new AssertionError();
        final Pupil pupil = (Pupil) getArguments().getSerializable("pupil");
        TextView confirmationTextView = view.findViewById(R.id.sign_in_confirmation_textView);
        String confirmationText = view.getResources().getString(R.string.pupil_selection_confirmation, pupil.name);
        confirmationTextView.setText(confirmationText);
        Button okButton = view.findViewById(R.id.sign_in_ok_btn);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onOkClick(SignInConfirmationDialog.this, pupil);
            }
        });
        Button cancelButton = view.findViewById(R.id.sign_in_cancel_btn);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onCancelClick(SignInConfirmationDialog.this);
            }
        });
        return builder.create();
    }
}

