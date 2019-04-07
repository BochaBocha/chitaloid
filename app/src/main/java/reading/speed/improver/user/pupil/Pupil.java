package reading.speed.improver.user.pupil;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity
public class Pupil {

    @PrimaryKey
    public UUID id;
    public String name;
}
