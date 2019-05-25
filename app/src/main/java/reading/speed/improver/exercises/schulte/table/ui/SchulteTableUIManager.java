package reading.speed.improver.exercises.schulte.table.ui;

import android.app.Activity;
import android.graphics.*;
import android.os.Handler;
import android.text.TextPaint;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;
import reading.speed.improver.R;

public class SchulteTableUIManager {

    private TableLayout schulteTableView;
    private ImageView[][] schulteTableCells;
    private Integer[][] schulteMatrix;
    private float textSize = 100;
    private final int borderSize = 2;
    private final int padding = 40;
    private Activity exerciseActivity;
    private Handler handler;


    public interface SchulteTableUIListener {
        void onTableCellClick(final int row, final int column);
    }

    SchulteTableUIListener mListener;

    private void attachListener(Activity activity) {
        try {
            mListener = (SchulteTableUIListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    public SchulteTableUIManager(Activity activity) {
        attachListener(activity);
        this.schulteTableView = activity.findViewById(R.id.schulte_table);
        handler = new Handler();
        this.exerciseActivity = activity;
    }

    public void fillSchulteTable() {
        for (int i = 0; i < schulteMatrix.length; i++) {
            for (int j = 0; j < schulteMatrix[i].length; j++) {
                Bitmap bitmap = createBitmapOutOfNumber(schulteMatrix[i][j]);
                (schulteTableCells[i][j]).setImageBitmap(bitmap);
            }
        }
    }

    private void initializeTableCells() {
        schulteTableCells = new ImageView[schulteMatrix.length][schulteMatrix[0].length];
        for (int i = 0; i < schulteMatrix.length; i++) {
            TableRow tableRow = new TableRow(exerciseActivity);

            for (int j = 0; j < schulteMatrix[i].length; j++) {
                ImageView imageView = new ImageView(exerciseActivity);
                tableRow.addView(imageView);
                schulteTableCells[i][j] = imageView;
            }
            schulteTableView.addView(tableRow);
            final LayoutParams lp = (LayoutParams) tableRow.getLayoutParams();
            lp.height = LayoutParams.MATCH_PARENT;
            lp.width = LayoutParams.WRAP_CONTENT;
            lp.gravity = Gravity.CENTER;
            tableRow.setLayoutParams(lp);
        }
    }

    public void setSchulteMatrix(Integer[][] schulteMatrix) {
        if (this.schulteMatrix == null) {
            this.schulteMatrix = schulteMatrix;
            initializeTableCells();
            setOnClickListenersForTableElements();
        }
        this.schulteMatrix = schulteMatrix;
        if (schulteMatrix.length != 0) {
            fillSchulteTable();
        }
    }

    public void setOnClickListenersForTableElements() {
        for (int i = 0; i < schulteMatrix.length; i++) {
            for (int j = 0; j < schulteMatrix[i].length; j++) {
                final int row = i;
                final int column = j;
                schulteTableCells[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.onTableCellClick(row, column);
                    }
                });
            }
        }
    }

    private Bitmap createBitmapOutOfNumber(final int number) {
        Bitmap bitmap = createTableCellBitmap(number, Color.WHITE);
        bitmap = addBorderToBitmap(bitmap);
        return bitmap;
    }

    private Bitmap createColoredBitmapOutOfNumber(final int number, final int backgroundColor) {
        Bitmap bitmap = createTableCellBitmap(number, backgroundColor);
        bitmap = addBorderToBitmap(bitmap);
        return bitmap;
    }

    private Bitmap createTableCellBitmap(final int number, final int backgroundColor) {
        float cellSize = calculateMinTableCellSize();
        Bitmap bitmap = Bitmap.createBitmap((int) cellSize + padding,
                (int) cellSize + padding,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawBitmap(bitmap, 0, 0, null);
        canvas.drawColor(backgroundColor);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(textSize);
        paint.setTextAlign(Paint.Align.CENTER);

        canvas.drawText(Integer.toString(number), bitmap.getWidth() / 2, bitmap.getHeight() - padding, paint);

        return bitmap;
    }

    private float calculateMinTableCellSize() {
        TextPaint textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(textSize);
        return textPaint.getTextSize();
    }

    private Bitmap addBorderToBitmap(final Bitmap srcBitmap) {
        Bitmap dstBitmap = Bitmap.createBitmap(
                srcBitmap.getWidth() + borderSize * 2,
                srcBitmap.getHeight() + borderSize * 2,
                Bitmap.Config.ARGB_8888
        );

        Canvas canvas = new Canvas(dstBitmap);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(borderSize);
        paint.setAntiAlias(true);

        Rect rect = new Rect(
                borderSize / 2,
                borderSize / 2,
                canvas.getWidth() - borderSize / 2,
                canvas.getHeight() - borderSize / 2
        );
        canvas.drawRect(rect, paint);

        canvas.drawBitmap(srcBitmap, borderSize, borderSize, null);

        return dstBitmap;
    }

    private void colorTableCellForMillis(final int row, final int column, final int backgroundColor) {
        (schulteTableCells[row][column]).invalidate();
        (schulteTableCells[row][column]).setImageBitmap(
                createColoredBitmapOutOfNumber(schulteMatrix[row][column], backgroundColor));
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                (schulteTableCells[row][column]).invalidate();
                colorTableCellPermanent(row, column, Color.WHITE);
            }
        }, 150);
    }

    private void colorTableCellPermanent(final int row, final int column, final int backgroundColor) {
        (schulteTableCells[row][column]).invalidate();
        (schulteTableCells[row][column]).setImageBitmap(
                createColoredBitmapOutOfNumber(schulteMatrix[row][column], backgroundColor));
    }

    public void indicateNumberIsFound(final int row, final int column) {
        colorTableCellForMillis(row, column, Color.GREEN);
    }

    public void indicateWrongNumberFound(final int row, final int column) {
        colorTableCellForMillis(row, column, Color.RED);
    }

    public void changeTextSize(final float textSize) {
        this.textSize = textSize;
        fillSchulteTable();
    }
}
