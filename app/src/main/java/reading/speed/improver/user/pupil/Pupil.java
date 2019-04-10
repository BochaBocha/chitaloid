package reading.speed.improver.user.pupil;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity
public class Pupil {

    @PrimaryKey(autoGenerate = true)
    public String _id;
    public String name;

    public Pupil(String _id, String name){
        this._id = _id;
        this.name = name;
    }
}
