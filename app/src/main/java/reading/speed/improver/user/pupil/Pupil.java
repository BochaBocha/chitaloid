package reading.speed.improver.user.pupil;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import org.jetbrains.annotations.NotNull;

@Entity(tableName = "Pupil")
public class Pupil {

    @PrimaryKey
    @ColumnInfo(name = "_id")
    @NotNull
    public String _id;
    @ColumnInfo(name = "name")
    @NotNull
    public String name;

    public Pupil(String _id, String name){
        this._id = _id;
        this.name = name;
    }
}
