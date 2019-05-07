package reading.speed.improver.exercises.ui;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import reading.speed.improver.R;

public class SchulteTableUIManager {

    private TableLayout schulteTableView;
    private ImageView[][] schulteTableCells;
    private int textSize;
    private final int borderSize = 3;


    public interface ExerciseMenuDialogListener {
        void onTableCellClick(final int row, final int column);
    }

    ExerciseMenuDialogListener mListener;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (ExerciseMenuDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    SchulteTableUIManager(TableLayout schulteTableView) {
        this.schulteTableView = schulteTableView;
        schulteTableCells = new ImageView[3][3];
        ImageView[] firstRow = {schulteTableView.findViewById(R.id.schulte_table_3x3_1_1),
                schulteTableView.findViewById(R.id.schulte_table_3x3_1_2),
                schulteTableView.findViewById(R.id.schulte_table_3x3_1_3)
        };
        schulteTableCells[0] = firstRow;
        ImageView[] secondRow = {schulteTableView.findViewById(R.id.schulte_table_3x3_2_1),
                schulteTableView.findViewById(R.id.schulte_table_3x3_2_2),
                schulteTableView.findViewById(R.id.schulte_table_3x3_2_3)
        };
        schulteTableCells[1] = secondRow;
        ImageView[] thirdRow = {schulteTableView.findViewById(R.id.schulte_table_3x3_3_1),
                schulteTableView.findViewById(R.id.schulte_table_3x3_3_2),
                schulteTableView.findViewById(R.id.schulte_table_3x3_3_3)
        };
        schulteTableCells[2] = thirdRow;
    }

    void fillSchulteTable(Integer[][] schulteMatrix) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Bitmap bitmap = createBitmapOutOfNumber(schulteMatrix[i][j]);
                (schulteTableCells[i][j]).setImageBitmap(bitmap);
            }
        }
    }

    void setOnClickListenersForTableElements() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final TextView schulteTableCell = schulteTableCells[i][j];
                schulteTableCells[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int numberClicked = Integer.parseInt(schulteTableCell.getText().toString());
                        if (exerciseViewModel.isCurrentNumFound(numberClicked)) {
                            schulteTableCell.setText("");
                            exerciseViewModel.increaseCurrentNum();
                        }
                    }
                });
            }
        }
    }

    private Bitmap createBitmapOutOfNumber(final int number) {
        TextView textView = createTextViewOutOfNumber(number);
        Bitmap bitmap = createBitmapFromTextView(textView);
        bitmap = addBorderToBitmap(bitmap);
        return bitmap;
    }

    private TextView createTextViewOutOfNumber(final int number) {
        TextView textView = new TextView(null);
        textView.setPadding(10, 10, 10, 10);
        textView.setTextSize(textSize);
        textView.setText(Integer.toString(number));
        return textView;

    }

    private Bitmap createBitmapFromTextView(TextView textView) {
        textView.buildDrawingCache();
        return textView.getDrawingCache();
    }


    private Bitmap addBorderToBitmap(final Bitmap bmp) {
        Bitmap bmpWithBorder = Bitmap.createBitmap(bmp.getWidth() + borderSize * 2,
                bmp.getHeight() + borderSize * 2, bmp.getConfig());
        Canvas canvas = new Canvas(bmpWithBorder);
        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(bmp, borderSize, borderSize, null);
        return bmpWithBorder;
    }

}
