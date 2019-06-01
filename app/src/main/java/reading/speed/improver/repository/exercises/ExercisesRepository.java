package reading.speed.improver.repository.exercises;

import reading.speed.improver.db.AppDatabase;
import reading.speed.improver.exercises.text.appearence.emerging.EmergingTextExercise;
import reading.speed.improver.exercises.text.appearence.fading.FadingTextExercise;
import reading.speed.improver.exercises.text.appearence.fading.ui.FadingTextExerciseActivity;
import reading.speed.improver.exercises.params.DefaultExerciseParams;
import reading.speed.improver.exercises.params.DefaultExerciseParamsBuilder;
import reading.speed.improver.exercises.text.appearence.emerging.ui.EmergingTextExerciseActivity;
import reading.speed.improver.exercises.schulte.table.SchulteExercise;
import reading.speed.improver.exercises.schulte.table.params.SchulteTableExerciseParams;
import reading.speed.improver.exercises.schulte.table.params.SchulteTableExerciseParamsBuilder;
import reading.speed.improver.exercises.schulte.table.ui.SchulteExerciseActivity;
import reading.speed.improver.exercises.text.misplaced.spaces.MisplacedSpacesExercise;
import reading.speed.improver.exercises.text.misplaced.spaces.ui.MisplacedSpacesExerciseActivity;
import reading.speed.improver.exercises.text.spaceless.SpacelessTextExercise;
import reading.speed.improver.exercises.text.spaceless.ui.SpacelessTextExerciseActivity;
import reading.speed.improver.exercises.word.appearence.EmergingWordsExercise;
import reading.speed.improver.exercises.word.appearence.ui.EmergingWordsExerciseActivity;

import java.util.HashMap;
import java.util.Map;

public class ExercisesRepository {
    private AppDatabase mAppDataBase;
    private Map<String, Exercises> exercises;
    private Exercises currentExercise;

    public ExercisesRepository(final AppDatabase mAppDataBase) {
        this.mAppDataBase = mAppDataBase;
        exercises = new HashMap<>();
        exercises.put(Exercises.SCHULTE_TABLE_SMALL.getTitle(), Exercises.SCHULTE_TABLE_SMALL);
        exercises.put(Exercises.SCHULTE_TABLE_MEDIUM.getTitle(), Exercises.SCHULTE_TABLE_MEDIUM);
        exercises.put(Exercises.SCHULTE_TABLE_LARGE.getTitle(), Exercises.SCHULTE_TABLE_LARGE);
        exercises.put(Exercises.SCHULTE_TABLE_EXTRA_LARGE.getTitle(), Exercises.SCHULTE_TABLE_EXTRA_LARGE);
        exercises.put(Exercises.EMERGING_TEXT.getTitle(), Exercises.EMERGING_TEXT);
        exercises.put(Exercises.FADING_TEXT.getTitle(), Exercises.FADING_TEXT);
        exercises.put(Exercises.SPACELESS_TEXT.getTitle(), Exercises.SPACELESS_TEXT);
        exercises.put(Exercises.MISPLACED_SPACES_TEXT.getTitle(), Exercises.MISPLACED_SPACES_TEXT);
        exercises.put(Exercises.EMERGING_WORDS.getTitle(), Exercises.EMERGING_WORDS);
    }

    public Map<String, Exercises> getExercises() {
        return exercises;
    }

    public Class getExerciseActivity() {
        switch (currentExercise) {
            case SCHULTE_TABLE_SMALL:
                return SchulteExerciseActivity.class;
            case SCHULTE_TABLE_MEDIUM:
                return SchulteExerciseActivity.class;
            case SCHULTE_TABLE_LARGE:
                return SchulteExerciseActivity.class;
            case SCHULTE_TABLE_EXTRA_LARGE:
                return SchulteExerciseActivity.class;
            case EMERGING_TEXT:
                return EmergingTextExerciseActivity.class;
            case FADING_TEXT:
                return FadingTextExerciseActivity.class;
            case SPACELESS_TEXT:
                return SpacelessTextExerciseActivity.class;
            case MISPLACED_SPACES_TEXT:
                return MisplacedSpacesExerciseActivity.class;
            case EMERGING_WORDS:
                return EmergingWordsExerciseActivity.class;
            default:
                return SchulteExerciseActivity.class;
        }
    }

    public void setCurrentExercise(Exercises exercise) {
        this.currentExercise = exercise;
    }

    private SchulteTableExerciseParams createShulteTableExerciseParams(final String title, final int size) {
        SchulteTableExerciseParamsBuilder paramsBuilder = new SchulteTableExerciseParamsBuilder();
        paramsBuilder.setName(title);
        paramsBuilder.setTableSize(size);
        return new SchulteTableExerciseParams(paramsBuilder);
    }

    public SchulteExercise createSchulteTableExercise() {
        SchulteTableExerciseParams schulteTableExerciseParams;
        switch (currentExercise) {
            case SCHULTE_TABLE_SMALL:
                schulteTableExerciseParams = createShulteTableExerciseParams(currentExercise.getTitle(), 3);
                break;
            case SCHULTE_TABLE_MEDIUM:
                schulteTableExerciseParams = createShulteTableExerciseParams(currentExercise.getTitle(), 4);
                break;
            case SCHULTE_TABLE_LARGE:
                schulteTableExerciseParams = createShulteTableExerciseParams(currentExercise.getTitle(), 5);
                break;
            case SCHULTE_TABLE_EXTRA_LARGE:
                schulteTableExerciseParams = createShulteTableExerciseParams(currentExercise.getTitle(), 6);
                break;
            default:
                schulteTableExerciseParams = createShulteTableExerciseParams(
                        Exercises.SCHULTE_TABLE_MEDIUM.getTitle(), 4);

        }
        return new SchulteExercise(schulteTableExerciseParams);
    }

    public EmergingTextExercise createEmergingTextExercise() {
        DefaultExerciseParamsBuilder paramsBuilder = new DefaultExerciseParamsBuilder();
        paramsBuilder.setName(Exercises.EMERGING_TEXT.getTitle());
        DefaultExerciseParams exerciseParams = new DefaultExerciseParams(paramsBuilder);

        return new EmergingTextExercise(exerciseParams);
    }

    public FadingTextExercise createFadingTextExercise() {
        DefaultExerciseParamsBuilder paramsBuilder = new DefaultExerciseParamsBuilder();
        paramsBuilder.setName(Exercises.FADING_TEXT.getTitle());
        DefaultExerciseParams exerciseParams = new DefaultExerciseParams(paramsBuilder);

        return new FadingTextExercise(exerciseParams);
    }

    public SpacelessTextExercise createSpacelessTextExercise() {
        DefaultExerciseParamsBuilder paramsBuilder = new DefaultExerciseParamsBuilder();
        paramsBuilder.setName(Exercises.SPACELESS_TEXT.getTitle());
        DefaultExerciseParams exerciseParams = new DefaultExerciseParams(paramsBuilder);

        return new SpacelessTextExercise(exerciseParams);
    }

    public MisplacedSpacesExercise createMisplacedSpacesExercise() {
        DefaultExerciseParamsBuilder paramsBuilder = new DefaultExerciseParamsBuilder();
        paramsBuilder.setName(Exercises.MISPLACED_SPACES_TEXT.getTitle());
        DefaultExerciseParams exerciseParams = new DefaultExerciseParams(paramsBuilder);

        return new MisplacedSpacesExercise(exerciseParams);
    }

    public EmergingWordsExercise createEmergingWordsExercise() {
        DefaultExerciseParamsBuilder paramsBuilder = new DefaultExerciseParamsBuilder();
        paramsBuilder.setName(Exercises.EMERGING_WORDS.getTitle());
        DefaultExerciseParams exerciseParams = new DefaultExerciseParams(paramsBuilder);

        return new EmergingWordsExercise(exerciseParams);
    }

    public void invalidateCurrentExercise() {
        currentExercise = null;
    }

}
