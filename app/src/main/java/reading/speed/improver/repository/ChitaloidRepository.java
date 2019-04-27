package reading.speed.improver.repository;

import android.content.Context;
import reading.speed.improver.db.AppDatabase;
import reading.speed.improver.db.DatabaseCopier;
import reading.speed.improver.user.pupil.Pupil;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class ChitaloidRepository {
    private static final ChitaloidRepository ourInstance = new ChitaloidRepository();
    private AppDatabase mAppDataBase;
    private PupilsRepository pupilsRepository;

    public static ChitaloidRepository getInstance() {
        return ourInstance;
    }

    public void init(Context context) {
        Context appContext = context;
        DatabaseCopier databaseCopier = new DatabaseCopier(appContext);
        mAppDataBase = databaseCopier.getRoomDatabase();
        pupilsRepository = new PupilsRepository(mAppDataBase);
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

    public void deletePupil(String name) {
        // todo: implement it
    }
}
