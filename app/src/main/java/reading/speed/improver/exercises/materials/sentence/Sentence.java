package reading.speed.improver.exercises.materials.sentence;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Sentence {
    @PrimaryKey(autoGenerate = true)
    public Integer _id;
    public String content;
}