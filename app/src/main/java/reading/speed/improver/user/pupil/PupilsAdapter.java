package reading.speed.improver.user.pupil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import org.jetbrains.annotations.NotNull;
import reading.speed.improver.R;

import java.util.List;

public class PupilsAdapter extends ArrayAdapter<Pupil> {


    public PupilsAdapter(@NonNull Context context, int resource, @NonNull List<Pupil> objects) {
        super(context, resource, objects);
    }

    public interface PupilsAdapterListener {
        void onPupilClick(Pupil pupil);
    }

    private PupilsAdapterListener mListener;

    public void subscribeListener(PupilsAdapterListener pupilsAdapterListener) {
        mListener = pupilsAdapterListener;
    }


    @NotNull
    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_pupil, parent, false);
        }

        Pupil pupil = getItem(position);
        TextView textView = convertView.findViewById(R.id.pupil_textView);
        textView.setText(pupil.name);
        textView.setTag(position);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = (Integer) view.getTag();
                Pupil pupil = getItem(position);
                mListener.onPupilClick(pupil);
            }
        });
        return convertView;
    }
}
