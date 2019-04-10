package reading.speed.improver.materials;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Sentence {
    @PrimaryKey(autoGenerate = true)
    public Integer _id;
    public String content;
}