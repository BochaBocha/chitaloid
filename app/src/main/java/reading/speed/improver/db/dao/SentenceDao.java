package reading.speed.improver.db.dao;

import androidx.room.*;
import reading.speed.improver.exercises.materials.sentence.Sentence;

import java.util.List;

@Dao
public interface SentenceDao {
    @Query("SELECT * FROM Sentence")
    List<Sentence> getAll();

    @Query("SELECT * FROM Sentence ORDER BY RANDOM() LIMIT 1")
    Sentence getRandom();

    @Query("SELECT * FROM Sentence WHERE LENGTH(content)>=:minAmountOfLetters AND LENGTH(content)<= :maxAmountOfLetters order by Random() limit 1")
    Sentence getRandom(Integer minAmountOfLetters, Integer maxAmountOfLetters);

    @Insert
    void insert(Sentence text);

    @Update
    void update(Sentence text);

    @Delete
    void delete(Sentence text);
}
