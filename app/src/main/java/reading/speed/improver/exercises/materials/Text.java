package reading.speed.improver.exercises.materials;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import org.jetbrains.annotations.NotNull;

@Entity(tableName = "Text")
public class Text {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="_id")
    @NotNull
    public Integer _id;
    @ColumnInfo(name = "content")
    @NotNull
    public String content;
}