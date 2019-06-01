package reading.speed.improver.repository;

import android.os.AsyncTask;
import reading.speed.improver.db.AppDatabase;
import reading.speed.improver.db.dao.StatisticDao;
import reading.speed.improver.statistic.Statistic;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class StatisticsRepository {
    private AppDatabase mAppDataBase;

    StatisticsRepository(AppDatabase mAppDataBase) {
        this.mAppDataBase = mAppDataBase;
    }

    List<Statistic> getByPupilId(final Integer pupil_id) throws ExecutionException, InterruptedException {

        return new getByPupilIdAsyncTask(mAppDataBase.getStatisticDao()).execute(pupil_id).get();
    }

    private static class getByPupilIdAsyncTask extends AsyncTask<Integer, Void, List<Statistic>> {

        private StatisticDao mAsyncTaskDao;

        getByPupilIdAsyncTask(StatisticDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected List<Statistic> doInBackground(Integer... integers) {
            return mAsyncTaskDao.getByPupilId(integers[0]);
        }
    }

    void insert(final Statistic statistic) {

        new insertAsyncTask(mAppDataBase.getStatisticDao()).execute(statistic);
    }

    private static class insertAsyncTask extends AsyncTask<Statistic, Void, Void> {

        private StatisticDao mAsyncTaskDao;

        insertAsyncTask(StatisticDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Statistic... statistics) {
            mAsyncTaskDao.insert(statistics[0]);
            return null;
        }
    }

    public void delete(final Integer pupil_id) {
        new deleteAsyncTask(mAppDataBase.getStatisticDao()).execute(pupil_id);
    }

    private static class deleteAsyncTask extends AsyncTask<Integer, Void, Void> {

        private StatisticDao mAsyncTaskDao;

        deleteAsyncTask(StatisticDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Integer... integers) {
            mAsyncTaskDao.delete(integers[0]);
            return null;
        }
    }
}

