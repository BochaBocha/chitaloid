package reading.speed.improver.user.pupil.management;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.fragment.app.DialogFragment;
import com.google.android.material.textfield.TextInputLayout;
import org.jetbrains.annotations.NotNull;
import reading.speed.improver.R;
import reading.speed.improver.service.ChitaloidService;
import reading.speed.improver.user.pupil.Pupil;
import reading.speed.improver.utils.InputValidator;

public class CreatePupilDialog extends DialogFragment {
    private TextInputLayout createPupilInput;

    public interface CreatePupilDialogListener {
        void onPupilCreatedClick(DialogFragment dialog, Pupil pupil);

        void onCancelClick(DialogFragment dialog);
    }

    private CreatePupilDialogListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (CreatePupilDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement CreatePupilDialogListener");
        }
    }

    @NotNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.create_pupil_dialog, null);
        builder.setView(view);
        createPupilInput = view.findViewById(R.id.create_pupil_input);

        Button okButton = view.findViewById(R.id.create_pupil_ok_btn);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPupil();
            }
        });
        Button cancelButton = view.findViewById(R.id.create_pupil_cancel_btn);
        cancelButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.onCancelClick(CreatePupilDialog.this);
                    }
                });
        return builder.create();
    }

    private void createPupil() {
        String pupilName = createPupilInput.getEditText().getText().toString(); //WTF
        InputValidator inputValidator = new InputValidator();
        switch (inputValidator.isUserNameValid(pupilName)) {
            case SUCCESS: {
                ChitaloidService chitaloidService = new ChitaloidService();
                Pupil pupil = chitaloidService.createPupil(pupilName);
                chitaloidService.addPupil(pupil);
                mListener.onPupilCreatedClick(this, pupil);
                break;
            }
            case TO_SHORT: {
                Toast toast = Toast.makeText(getContext(), getString(R.string.name_is_too_short), Toast.LENGTH_SHORT);
                toast.show();
                break;
            }
            case ALREADY_TAKEN: {
                Toast toast = Toast.makeText(getContext(), getString(R.string.name_is_already_taken), Toast.LENGTH_SHORT);
                toast.show();
                break;
            }
        }
    }
}

