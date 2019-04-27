package reading.speed.improver.db.dao;

import androidx.room.*;
import reading.speed.improver.user.pupil.Pupil;

import java.util.List;

@Dao
public interface PupilDao {

    @Query("SELECT * FROM Pupil")
    List<Pupil> getAll();

    @Query("SELECT * FROM Pupil WHERE _id = :id")
    Pupil getById(String id);

    @Insert
    void insert(Pupil pupil);

    @Update
    void update(Pupil pupil);

    @Delete
    void delete(Pupil pupil);

}
