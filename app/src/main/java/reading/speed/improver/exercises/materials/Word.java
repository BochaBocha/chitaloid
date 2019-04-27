package reading.speed.improver.exercises.materials;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import org.jetbrains.annotations.NotNull;

@Entity(tableName = "Word")
public class Word {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="_id")
    @NotNull
    public int _id;
    @ColumnInfo(name = "content")
    @NotNull
    public String content;
}
