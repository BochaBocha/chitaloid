package reading.speed.improver.db.dao;

import androidx.room.*;
import reading.speed.improver.exercises.materials.Text;

import java.util.List;

@Dao
public interface TextDao {

    @Query("SELECT * FROM Text")
    List<Text> getAll();

    @Query("SELECT count(_id) FROM Text")
    int getCount();

    @Query("SELECT * FROM Text WHERE _id = :id")
    Text getById(int id);

    @Insert
    void insert(Text text);

    @Update
    void update(Text text);

    @Delete
    void delete(Text text);

}
