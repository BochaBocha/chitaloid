package reading.speed.improver.repository;

import android.os.AsyncTask;
import reading.speed.improver.db.AppDatabase;
import reading.speed.improver.db.dao.WordDao;
import reading.speed.improver.exercises.materials.Word;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class WordsRepository {
    private AppDatabase mAppDataBase;

    WordsRepository(AppDatabase mAppDataBase) {
        this.mAppDataBase = mAppDataBase;
    }

    public Word getRandomWord(final int amountOfLetters) 
        throws ExecutionException, InterruptedException {
        return new  getRandomWordAsyncTask(mAppDataBase.getWordDao()).execute(amountOfLetters).get();
    }
    ...
    private static class getRandomWordAsyncTask extends AsyncTask<Integer, Void, Word> {

        private WordDao mAsyncTaskDao;

        getTextAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Word doInBackground(Integer... integers) {
            return mAsyncTaskDao.getRandom(integers[0]);
        }
    }
    
    
    List<Word> getAllWords() throws ExecutionException, InterruptedException {

        return new getAllAsyncTask(mAppDataBase.getWordDao()).execute().get();
    }

    private static class getAllAsyncTask extends AsyncTask<Void, Void, List<Word>> {

        private WordDao mAsyncTaskDao;

        getAllAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected List<Word> doInBackground(Void... voids) {
            return mAsyncTaskDao.getAll();
        }
    }

}
