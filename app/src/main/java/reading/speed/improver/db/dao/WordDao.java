package reading.speed.improver.db.dao;

import androidx.room.*;
import reading.speed.improver.exercises.materials.Word;

import java.util.List;

@Dao
public interface WordDao {

    @Query("SELECT * FROM Word")
    List<Word> getAll();

    @Query("SELECT * FROM Word WHERE LENGTH(content)=:amountOfLetters ORDER BY Random() LIMIT 1;")
    Word getRandom(Integer amountOfLetters);
}
