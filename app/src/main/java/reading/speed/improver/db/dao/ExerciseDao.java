package reading.speed.improver.db.dao;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface ExerciseDao {
    @Query("SELECT (name) FROM Exercise WHERE _id=:id")
    String getNameById(Integer id);

    @Query("SELECT (_id) FROM Exercise WHERE name=:name")
    Integer getIdByName(String name);
}
