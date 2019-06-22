package reading.speed.improver.user.pupil;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Entity(tableName = "Pupil")
public class Pupil implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    @NotNull
    public Integer _id;
    @ColumnInfo(name = "name")
    @NotNull
    public String name;

    public Pupil(@NotNull Integer _id, @NotNull String name){
        this._id = _id;
        this.name = name;
    }
}
