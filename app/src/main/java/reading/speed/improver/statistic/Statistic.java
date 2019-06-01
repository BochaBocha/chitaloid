package reading.speed.improver.statistic;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import org.jetbrains.annotations.NotNull;
import reading.speed.improver.service.DateConverter;

import java.util.Date;

@Entity(tableName = "Statistic")
@TypeConverters(DateConverter.class)
public class Statistic {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    @NotNull
    public Integer _id;
    @ColumnInfo(name = "pupil_id")
    @NotNull
    public Integer pupil_id;
    @ColumnInfo(name = "exercise_id")
    @NotNull
    public Integer exercise_id;
    @ColumnInfo(name = "date")
    @NotNull
    public Date date;
    @ColumnInfo(name = "lead_time_sec")
    @NotNull
    public Integer lead_time_sec;
    @ColumnInfo(name = "score")
    public Integer score;

    public Statistic(Integer _id,
                     Integer pupil_id,
                     Integer exercise_id,
                     Date date,
                     Integer lead_time_sec,
                     Integer score) {
        this._id = _id;
        this.pupil_id = pupil_id;
        this.exercise_id = exercise_id;
        this.date = date;
        this.lead_time_sec = lead_time_sec;
        this.score = score;
    }
}
