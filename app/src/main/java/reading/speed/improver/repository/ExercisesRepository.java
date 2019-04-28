package reading.speed.improver.repository;

import reading.speed.improver.db.AppDatabase;
import reading.speed.improver.exercises.ui.ExerciseActivity;

import java.util.HashMap;
import java.util.Map;

public class ExercisesRepository {
    private AppDatabase mAppDataBase;
    private Map<String, Class> exercises;
    public  ExercisesRepository(final AppDatabase mAppDataBase){
        this.mAppDataBase = mAppDataBase;
        exercises = new HashMap<>();
        exercises.put("Таблица Шульте", ExerciseActivity.class);
    }
    public Map<String, Class> getExercises(){
        return exercises;
    }
}
