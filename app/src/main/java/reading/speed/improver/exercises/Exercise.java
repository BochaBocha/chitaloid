package reading.speed.improver.exercises;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import org.jetbrains.annotations.NotNull;

@Entity(tableName = "Exercise")
public class Exercise {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    @NotNull
    public Integer _id;
    @ColumnInfo(name = "name")
    @NotNull
    public String name;

    public Exercise(Integer _id, String name) {
        this._id = _id;
        this.name = name;
    }
}
