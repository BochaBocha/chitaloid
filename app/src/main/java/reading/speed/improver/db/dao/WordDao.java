package reading.speed.improver.db.dao;

import androidx.room.*;
import reading.speed.improver.materials.Word;

import java.util.List;

@Dao
public interface WordDao {

    @Query("SELECT * FROM word")
    List<Word> getAll();

    @Query("SELECT * FROM word WHERE _id = :_id")
    Word getById(Integer _id);

    @Insert
    void insert(Word word);

    @Update
    void update(Word word);

    @Delete
    void delete(Word word);

}