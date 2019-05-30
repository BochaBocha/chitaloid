package reading.speed.improver.repository;

import android.os.AsyncTask;
import reading.speed.improver.db.AppDatabase;
import reading.speed.improver.db.dao.TextDao;
import reading.speed.improver.exercises.materials.Text;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class TextsRepository {
    private AppDatabase mAppDataBase;

    TextsRepository(AppDatabase mAppDataBase) {
        this.mAppDataBase = mAppDataBase;
    }

    public int getTextCount() throws ExecutionException, InterruptedException {
        return new getTextCountAsyncTask(mAppDataBase.getTextDao()).execute().get();
    }

    private static class getTextCountAsyncTask extends AsyncTask<Void, Void, Integer> {

        private TextDao mAsyncTaskDao;

        getTextCountAsyncTask(TextDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            return mAsyncTaskDao.getCount();
        }
    }

    public Text getText(final int id) throws ExecutionException, InterruptedException {
        return new  getTextAsyncTask(mAppDataBase.getTextDao()).execute(id).get();
    }

    private static class getTextAsyncTask extends AsyncTask<Integer, Void, Text> {

        private TextDao mAsyncTaskDao;

        getTextAsyncTask(TextDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Text doInBackground(Integer... integers) {
            return mAsyncTaskDao.getById(integers[0]);
        }
    }

    public List<Text> getAllTexts() throws ExecutionException, InterruptedException {
        return  new getAllTextsAsyncTask(mAppDataBase.getTextDao()).execute().get();
    }

    private static class getAllTextsAsyncTask extends AsyncTask<Void, Void, List<Text>> {

        private TextDao mAsyncTaskDao;

        getAllTextsAsyncTask(TextDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected List<Text> doInBackground(Void... voids) {
            return mAsyncTaskDao.getAll();
        }
    }
}
