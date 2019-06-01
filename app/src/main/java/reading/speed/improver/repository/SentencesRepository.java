package reading.speed.improver.repository;

import android.os.AsyncTask;
import reading.speed.improver.db.AppDatabase;
import reading.speed.improver.db.dao.SentenceDao;
import reading.speed.improver.exercises.materials.sentence.Sentence;

import java.util.concurrent.ExecutionException;

public class SentencesRepository {
    private AppDatabase mAppDataBase;

    SentencesRepository(AppDatabase mAppDataBase) {
        this.mAppDataBase = mAppDataBase;
    }

    public Sentence getRandomSentence() throws ExecutionException, InterruptedException {
        return new getRandomSentenceAsyncTask(mAppDataBase.getSentenceDao()).execute().get();
    }

    private static class getRandomSentenceAsyncTask extends AsyncTask<Void, Void, Sentence> {

        private SentenceDao mAsyncTaskDao;

        getRandomSentenceAsyncTask(SentenceDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Sentence doInBackground(Void... voids) {
            return mAsyncTaskDao.getRandom();
        }
    }

    public Sentence getRandomSentenceInRange(final int minAmountOfLetters, final int maxAmountOfLetters) throws ExecutionException, InterruptedException {
        return new getRandomSentenceInRangeAsyncTask(mAppDataBase.getSentenceDao()).execute(minAmountOfLetters, maxAmountOfLetters).get();
    }

    private static class getRandomSentenceInRangeAsyncTask extends AsyncTask<Integer, Void, Sentence> {

        private SentenceDao mAsyncTaskDao;

        getRandomSentenceInRangeAsyncTask(SentenceDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Sentence doInBackground(Integer... integers) {
            return mAsyncTaskDao.getRandom(integers[0], integers[1]);
        }
    }
}
