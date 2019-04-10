package reading.speed.improver.db.dao;

import androidx.room.*;
import reading.speed.improver.user.pupil.Pupil;

import java.util.List;

@Dao
public interface PupilDao {

    @Query("SELECT * FROM pupil")
    List<Pupil> getAll();

    @Query("SELECT * FROM pupil WHERE _id = :_id")
    Pupil getById(String _id);

    @Insert
    void insert(Pupil pupil);

    @Update
    void update(Pupil pupil);

    @Delete
    void delete(Pupil pupil);

}
