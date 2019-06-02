package reading.speed.improver.user.pupil;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import org.jetbrains.annotations.NotNull;

@Entity(tableName = "Pupil")
public class Pupil {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    @NotNull
    public Integer _id;
    @ColumnInfo(name = "name")
    @NotNull
    public String name;

    public Pupil(Integer _id, String name){
        this._id = _id;
        this.name = name;
    }
}
