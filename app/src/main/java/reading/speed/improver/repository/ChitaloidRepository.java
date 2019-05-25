package reading.speed.improver.repository;

import android.content.Context;
import reading.speed.improver.db.AppDatabase;
import reading.speed.improver.db.DatabaseCopier;
import reading.speed.improver.exercises.emerging.text.EmergingTextExercise;
import reading.speed.improver.exercises.schulte.table.SchulteExercise;
import reading.speed.improver.user.pupil.Pupil;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class ChitaloidRepository {
    private static final ChitaloidRepository ourInstance = new ChitaloidRepository();
    private AppDatabase mAppDataBase;
    private PupilsRepository pupilsRepository;
    private ExercisesRepository exercisesRepository;

    public static ChitaloidRepository getInstance() {
        return ourInstance;
    }

    public void init(Context context) {
        Context appContext = context;
        DatabaseCopier databaseCopier = new DatabaseCopier(appContext);
        mAppDataBase = databaseCopier.getRoomDatabase();
        pupilsRepository = new PupilsRepository(mAppDataBase);
        exercisesRepository = new ExercisesRepository(mAppDataBase);
    }

    public Pupil getCurrentPupil() {
        return pupilsRepository.getCurrentPupil();
    }

    public void setCurrentPupil(Pupil pupil) {
        pupilsRepository.setCurrentPupil(pupil);
    }

    public Pupil createPupil(String name) {
        return new Pupil(UUID.randomUUID().toString(), name);
    }

    public void addPupil(Pupil pupil) {
        pupilsRepository.addPupil(pupil);
    }

    public List<Pupil> getPupils() throws ExecutionException, InterruptedException {
        return pupilsRepository.getPupils();
    }

    public void deletePupil(Pupil pupil) {
        pupilsRepository.deletePupil(pupil);
    }

    public Pupil getPupilByName(String name) throws ExecutionException, InterruptedException {
        return pupilsRepository.getPupilByName(name);
    }

    public Map<String, Exercises> getExercises() {
        return exercisesRepository.getExercises();
    }


    public Class getExerciseActivity(Exercises exercise) {
        return exercisesRepository.getExerciseActivity(exercise);
    }

    public void createExerciseModel(Exercises exercise) {
        exercisesRepository.createExerciseModel(exercise);
    }

    public void invalidateCurrentExerciseModel() {
        exercisesRepository.invalidateCurrentExerciseModel();
    }

    public SchulteExercise getSchulteExerciseModel() {
        return exercisesRepository.getSchulteExercise();
    }

    public EmergingTextExercise getEmergingTextExerciseModel() {
        return exercisesRepository.getEmergingTextExercise();
    }
}
