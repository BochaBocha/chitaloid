package reading.speed.improver.repository;

import android.os.AsyncTask;
import reading.speed.improver.db.AppDatabase;
import reading.speed.improver.db.dao.PupilDao;
import reading.speed.improver.user.pupil.Pupil;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class PupilsRepository {
    private AppDatabase mAppDataBase;
    private Pupil currentPupil;

    PupilsRepository(AppDatabase mAppDataBase) {
        this.mAppDataBase = mAppDataBase;
        currentPupil = null;
    }


    Pupil getCurrentPupil() {
        return currentPupil;
    }

    void setCurrentPupil(Pupil currentPupil) {
        this.currentPupil = currentPupil;
    }

    public Pupil createPupil(String name){
        return new Pupil(UUID.randomUUID().toString(), name);
    }

    void addPupil(Pupil pupil) {
        new insertAsyncTask(mAppDataBase.getPupilDao()).execute(pupil);
    }

    List<Pupil> getPupils() throws ExecutionException, InterruptedException {

        return new getAllAsyncTask(mAppDataBase.getPupilDao()).execute().get();
    }

    public void deletePupil(String name) {
        // todo: implement it
    }


    private static class insertAsyncTask extends AsyncTask<Pupil, Void, Void> {

        private PupilDao mAsyncTaskDao;

        insertAsyncTask(PupilDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Pupil... pupils) {
            mAsyncTaskDao.insert(pupils[0]);
            return null;
        }
    }

    private static class getAllAsyncTask extends AsyncTask<Void, Void, List<Pupil>>{

        private PupilDao mAsyncTaskDao;

        getAllAsyncTask(PupilDao dao){
            mAsyncTaskDao = dao;
        }


        @Override
        protected List<Pupil> doInBackground(Void... voids) {
            return mAsyncTaskDao.getAll();
        }
    }
}
