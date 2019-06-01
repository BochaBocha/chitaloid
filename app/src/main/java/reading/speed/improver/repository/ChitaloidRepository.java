package reading.speed.improver.repository;

import android.content.Context;
import reading.speed.improver.db.AppDatabase;
import reading.speed.improver.db.DatabaseCopier;
import reading.speed.improver.exercises.materials.sentence.Sentence;
import reading.speed.improver.exercises.text.appearence.emerging.EmergingTextExercise;
import reading.speed.improver.exercises.materials.Text;
import reading.speed.improver.exercises.materials.Word;
import reading.speed.improver.exercises.schulte.table.SchulteExercise;
import reading.speed.improver.exercises.text.appearence.fading.FadingTextExercise;
import reading.speed.improver.exercises.text.misplaced.spaces.MisplacedSpacesExercise;
import reading.speed.improver.exercises.text.spaceless.SpacelessTextExercise;
import reading.speed.improver.exercises.word.appearence.EmergingWordsExercise;
import reading.speed.improver.repository.exercises.Exercises;
import reading.speed.improver.repository.exercises.ExercisesRepository;
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
    private TextsRepository textsRepository;
    private WordsRepository wordsRepository;
    private SentenceRepository sentenceRepository;

    public static ChitaloidRepository getInstance() {
        return ourInstance;
    }

    public void init(Context context) {
        if (mAppDataBase != null) {
            return;
        }
        Context appContext = context;
        DatabaseCopier databaseCopier = new DatabaseCopier(appContext);
        mAppDataBase = databaseCopier.getRoomDatabase();
        pupilsRepository = new PupilsRepository(mAppDataBase);
        exercisesRepository = new ExercisesRepository(mAppDataBase);
        textsRepository = new TextsRepository(mAppDataBase);
        wordsRepository = new WordsRepository(mAppDataBase);
        sentenceRepository = new SentenceRepository(mAppDataBase);
    }

    public Pupil getCurrentPupil() {
        return pupilsRepository.getCurrentPupil();
    }

    public void setCurrentPupil(final Pupil pupil) {
        pupilsRepository.setCurrentPupil(pupil);
    }

    public Pupil createPupil(final String name) {
        return new Pupil(UUID.randomUUID().toString(), name);
    }

    public void addPupil(final Pupil pupil) {
        pupilsRepository.addPupil(pupil);
    }

    public List<Pupil> getPupils() throws ExecutionException, InterruptedException {
        return pupilsRepository.getPupils();
    }

    public void deletePupil(final Pupil pupil) {
        pupilsRepository.deletePupil(pupil);
    }

    public Pupil getPupilByName(String name) throws ExecutionException, InterruptedException {
        return pupilsRepository.getPupilByName(name);
    }

    public Sentence getRandomSentence() {
        try {
            return sentenceRepository.getRandomSentence();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, Exercises> getExercises() {
        return exercisesRepository.getExercises();
    }


    public Class getExerciseActivity() {
        return exercisesRepository.getExerciseActivity();
    }

    public void setCurrentExercise(Exercises exercise) {
        exercisesRepository.setCurrentExercise(exercise);
    }

    public void invalidateCurrentExercise() {
        exercisesRepository.invalidateCurrentExercise();
    }

    public SchulteExercise createSchulteTableExercise() {
        return exercisesRepository.createSchulteTableExercise();
    }

    public EmergingTextExercise createEmergingTextExercise() {
        return exercisesRepository.createEmergingTextExercise();
    }

    public FadingTextExercise createFadingTextExercise() {
        return exercisesRepository.createFadingTextExercise();
    }

    public SpacelessTextExercise createSpacelessTextExercise() {
        return exercisesRepository.createSpacelessTextExercise();
    }

    public MisplacedSpacesExercise createMisplacedSpacesExercise() {
        return exercisesRepository.createMisplacedSpacesExercise();
    }

    public EmergingWordsExercise createEmergingWordsExercise() {
        return exercisesRepository.createEmergingWordsExercise();
    }

    public Text getText(final int id) throws ExecutionException, InterruptedException {
        return textsRepository.getText(id);
    }

    public Word getRandomWord(final int amountOfLetters) throws ExecutionException, InterruptedException {
        return wordsRepository.getRandomWord(amountOfLetters);
    }

    public int getTextCount() throws ExecutionException, InterruptedException {
        return textsRepository.getTextCount();
    }

    public List<Text> getAllTexts() throws ExecutionException, InterruptedException {
        return textsRepository.getAllTexts();
    }

    public List<Word> getAllWords() throws ExecutionException, InterruptedException {
        return wordsRepository.getAllWords();
    }

    public Sentence getRandomSentence(final int minAmountOfLetters, final int maxAmountOfLetters) {
        try {
            return sentenceRepository.getRandomSentenceInRange(minAmountOfLetters, maxAmountOfLetters);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

}
