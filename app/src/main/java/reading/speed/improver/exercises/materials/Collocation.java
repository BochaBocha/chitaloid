package reading.speed.improver.exercises.materials;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Collocation {
    @PrimaryKey(autoGenerate = true)
    public Integer _id;
    public String content;
}