package reading.speed.improver.repository;

import android.content.Context;
import reading.speed.improver.db.AppDatabase;
import reading.speed.improver.user.pupil.Pupil;

import java.util.List;
import java.util.UUID;

public class ChitaloidRepository {
    private static final ChitaloidRepository ourInstance = new ChitaloidRepository();
    private static Context appContext;

    private Pupil currentPupil;
    private AppDatabase mAppDataBase;

    public Pupil getCurrentPupil() {
        return currentPupil;
    }

    public void setCurrentPupil(Pupil currentPupil) {
        this.currentPupil = currentPupil;
    }



    public static ChitaloidRepository getInstance(Context context) {
        appContext = context;
        return ourInstance;
    }
    public static ChitaloidRepository getInstance() {
        return ourInstance;
    }

    private ChitaloidRepository() {
        DatabaseCopier databaseCopier = new DatabaseCopier(appContext);
        mAppDataBase = databaseCopier.getRoomDatabase();
        currentPupil = null;
    }

    public void addPupil(String name){
        Pupil pupil = new Pupil(UUID.randomUUID().toString(), name);
        mAppDataBase.getPupilDao().insert(pupil);
    }

    public List<Pupil> getPupils(){
        return mAppDataBase.getPupilDao().getAll();
    }

    public void deletePupil(String name){
        // todo: implement it
    }


}
