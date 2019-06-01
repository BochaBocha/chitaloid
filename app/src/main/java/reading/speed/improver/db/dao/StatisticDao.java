package reading.speed.improver.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import reading.speed.improver.statistic.Statistic;

import java.util.List;

@Dao
public interface StatisticDao {

    @Query("SELECT * FROM Statistic WHERE (pupil_id)=:pupil_id")
    List<Statistic> getByPupilId(Integer pupil_id);

    @Insert
    void insert(Statistic statistic);

    @Query("DELETE FROM Statistic WHERE pupil_id = :pupil_id")
    void delete(Integer pupil_id);

}
